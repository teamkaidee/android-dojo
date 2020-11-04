package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviActionMapper
import com.app.kaidee.arch.mvi.MviPresenter
import com.app.kaidee.arch.mvi.MviProcessorHolder
import com.app.kaidee.arch.mvi.MviReducerHolder
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class CounterPresenter @Inject constructor(
	initialState: CounterViewState,
	schedulerProvider: SchedulerProvider,
	processorHolder: MviProcessorHolder<CounterAction, CounterResult>,
	reducerHolder: MviReducerHolder<CounterResult, CounterViewState>,
	actionMapper: MviActionMapper<CounterIntent, CounterViewState, CounterAction>,
	private val routerMapper: CounterRouterMapper
) : MviPresenter<CounterIntent, CounterViewState, CounterAction, CounterResult>(
	initialState,
	schedulerProvider,
	processorHolder,
	reducerHolder,
	actionMapper
) {

	private var isInitialized = false

	fun navigation(): Observable<CounterRouter> {
		return resultObservable.map(routerMapper::mapToRouter)
	}

	override fun dispatch(intent: CounterIntent) {
		if (intent is CounterIntent.InitialIntent) {
			if (!isInitialized) {
				isInitialized = true
				super.dispatch(intent)
			}
		} else {
			super.dispatch(intent)
		}
	}

}