package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.lite.MviLitePresenter
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.counter.navigation.CounterNavigator
import com.app.kaidee.counter.usecase.CheckIsWin
import com.app.kaidee.counter.usecase.GenerateGameSession
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CounterPresenter @Inject constructor(
	initialState: CounterViewState,
	schedulerProvider: SchedulerProvider,
	private val generateGameSession: GenerateGameSession,
	private val checkIsWin: CheckIsWin,
	private val navigator: CounterNavigator
) : MviLitePresenter<CounterIntent, CounterViewState, CounterResult>(initialState, schedulerProvider) {

	override fun processorHolder(intents: Observable<CounterIntent>): List<Observable<CounterViewState>> {
		return with(intents) {
			listOf(
				generateGoalProcessor(),
				increaseValueProcessor(),
				decreaseValueProcessor()
			)
		}
	}

	private fun Observable<CounterIntent>.generateGoalProcessor(): Observable<CounterViewState> {
		return filterIsInstance<CounterIntent.InitialIntent>()
			.processIntent()
			.reduceState()
	}

	private fun Observable<CounterIntent>.increaseValueProcessor(): Observable<CounterViewState> {
		return filterIsInstance<CounterIntent.IncreaseIntent>()
			.processIntent()
			.reduceState()
	}

	private fun Observable<CounterIntent>.decreaseValueProcessor(): Observable<CounterViewState> {
		return filterIsInstance<CounterIntent.DecreaseIntent>()
			.processIntent()
			.reduceState()
	}

	//region generate-goal
	private fun Observable<CounterIntent.InitialIntent>.processIntent(): Observable<CounterResult.GenerateGoalResult> {
		return flatMap {
			generateGameSession().map { (start, goal) ->
				CounterResult.GenerateGoalResult.Success(start, goal)
			}
				.cast(CounterResult.GenerateGoalResult::class.java)
				.onErrorReturn(CounterResult.GenerateGoalResult::Failure)
				.startWith(CounterResult.GenerateGoalResult.Loading)
		}
	}

	private fun Observable<CounterResult.GenerateGoalResult>.reduceState(): Observable<CounterViewState> {
		return map { result ->
			when (result) {
				is CounterResult.GenerateGoalResult.Loading -> setState {
					copy(
						isLoading = true,
						error = null
					)
				}
				is CounterResult.GenerateGoalResult.Success -> setState {
					copy(
						goal = result.goal,
						count = result.startNumber,
						isLoading = false,
						error = null
					)
				}
				is CounterResult.GenerateGoalResult.Failure -> setFailureState(result.throwable)
			}
		}
	}
	//endregion

	//region update-value
	@JvmName("processIncreaseValueIntent")
	private fun Observable<CounterIntent.IncreaseIntent>.processIntent(): Observable<CounterResult.UpdateValueResult> {
		return flatMap {
			updateValue(1)
		}
	}

	@JvmName("processDecreaseValueIntent")
	private fun Observable<CounterIntent.DecreaseIntent>.processIntent(): Observable<CounterResult.UpdateValueResult> {
		return flatMap {
			updateValue(-1)
		}
	}

	private fun updateValue(value: Int): Observable<CounterResult.UpdateValueResult> {
		return checkIsWin(value).map { isWin ->
			if (isWin) {
				CounterResult.UpdateValueResult.Win(value)
			} else {
				CounterResult.UpdateValueResult.Success(value)
			}
		}
			.cast(CounterResult.UpdateValueResult::class.java)
			.onErrorReturn(CounterResult.UpdateValueResult::Failure)
	}

	@JvmName("reduceIncreaseValueState")
	private fun Observable<CounterResult.UpdateValueResult>.reduceState(): Observable<CounterViewState> {
		return flatMap { result ->
			when (result) {
				is CounterResult.UpdateValueResult.Win -> {
					Completable.fromAction {
						navigator.navigateToResultPage()
					}.toObservable()
				}
				is CounterResult.UpdateValueResult.Success -> {
					Observable.just(setUpdateValueState(result.value))
				}
				is CounterResult.UpdateValueResult.Failure -> {
					Observable.just(setFailureState(result.throwable))
				}
			}
		}
	}
	//endregion

	//region common-set-state
	private fun setFailureState(throwable: Throwable) = setState {
		copy(
			isLoading = false,
			error = throwable
		)
	}

	private fun setUpdateValueState(value: Int) = setState {
		copy(
			count = currentState().count + value,
			error = null
		)
	}
	//endregion

}