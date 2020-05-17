package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviActionMapper
import com.app.kaidee.arch.mvi.MviPresenter
import com.app.kaidee.arch.mvi.MviProcessorHolder
import com.app.kaidee.arch.mvi.MviReducerHolder
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.counter.presentation.CounterIntent.InitialIntent
import javax.inject.Inject

class CounterPresenter @Inject constructor(
    initialState: CounterViewState,
    schedulerProvider: SchedulerProvider,
    processorHolder: MviProcessorHolder<CounterAction, CounterResult>,
    reducerHolder: MviReducerHolder<CounterResult, CounterViewState>,
    actionMapper: MviActionMapper<CounterIntent, CounterAction>
) : MviPresenter<CounterIntent, CounterViewState, CounterAction, CounterResult>(
    initialState,
    schedulerProvider,
    processorHolder,
    reducerHolder,
    actionMapper
) {

    init {
        dispatch(InitialIntent)
    }

}