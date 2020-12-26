package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.lite.MviLitePresenter
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.endlessscroll.data.SimpleItem
import com.app.kaidee.endlessscroll.data.SimpleItemRepository
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult.Error
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult.Success
import io.reactivex.Observable
import javax.inject.Inject
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent as Intent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult as Result
import com.app.kaidee.endlessscroll.presentation.EndlessScrollViewState as ViewState

class EndlessScrollPresenter @Inject constructor(
	schedulerProvider: SchedulerProvider,
	private val repository: SimpleItemRepository
) : MviLitePresenter<Intent, ViewState>(ViewState.idle(), schedulerProvider) {

	override fun processorHolder(intents: Observable<Intent>): List<Observable<ViewState>> {
		return with(intents) {
			listOf(
				initialProcessor(),
				loadMoreProcessor()
			)
		}
	}

	// region initial-processor
	private fun Observable<Intent>.initialProcessor(): Observable<ViewState> {
		return filterIsInstance<Intent.InitialIntent>()
			.processIntent()
			.reduceState()
	}

	@JvmName("processInitialIntent")
	private fun Observable<Intent.InitialIntent>.processIntent(): Observable<Result> {
		return flatMap {
			getItems(0)
		}
	}
	// endregion

	// region load-more-processor
	private fun Observable<Intent>.loadMoreProcessor(): Observable<ViewState> {
		return filterIsInstance<Intent.LoadMoreIntent>()
			.processIntent()
			.reduceState()
	}

	@JvmName("processLoadMoreIntent")
	private fun Observable<Intent.LoadMoreIntent>.processIntent(): Observable<Result> {
		return flatMap {
			getItems(currentState().offset)
		}
	}
	// endregion

	private fun Observable<Result>.reduceState(): Observable<ViewState> {
		return map { result ->
			when (result) {
				is Success -> setState {
					copy(
						offset = result.offset,
						items = mutableListOf<SimpleItem>().apply {
							addAll(items)
							addAll(result.items)
						},
						error = null
					)
				}
				is Error -> setState {
					copy(
						offset = 0,
						items = emptyList(),
						error = result.throwable
					)
				}
			}
		}
	}

	private fun getItems(offset: Int): Observable<Result> {
		return repository.getItems(offset, 15)
			.map { result ->
				Success(
					offset + 1,
					result
				)
			}
			.cast(Result::class.java)
			.onErrorReturn(::Error)
	}

}