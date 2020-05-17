package com.app.kaidee.counter.presentation

import android.util.Log
import com.app.kaidee.arch.mvi.MviActionMapper
import com.app.kaidee.arch.mvi.MviPresenter
import com.app.kaidee.arch.mvi.MviProcessorHolder
import com.app.kaidee.arch.mvi.MviReducerHolder
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.counter.presentation.CounterIntent.InitialIntent
import io.reactivex.Observable
import javax.inject.Inject

class CounterPresenter @Inject constructor(
    initialState: CounterViewState,
    schedulerProvider: SchedulerProvider,
    processorHolder: MviProcessorHolder<CounterAction, CounterResult>,
    reducerHolder: MviReducerHolder<CounterResult, CounterViewState>,
    actionMapper: MviActionMapper<CounterIntent, CounterAction>,
    private val routerMapper: CounterRouterMapper
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

    fun navigation(): Observable<CounterRouter> {
        return resultObservable.map(routerMapper::mapToRouter)
    }

    override fun log(message: String) {
        Log.d(TAG, message)
    }

    companion object {

        private val TAG = CounterPresenter::class.java.simpleName

    }

}