package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.arch.mvi.MviProcessorHolder
import com.app.kaidee.counter.presentation.CounterAction.*
import com.app.kaidee.counter.presentation.CounterResult.*
import io.reactivex.Observable
import javax.inject.Inject

class CounterProcessorHolder @Inject constructor(
    private val generateGoalProcessor: MviProcessor<GenerateGoalAction, GenerateGoalResult>,
    private val updateValueProcessor: MviProcessor<UpdateValueAction, UpdateValueResult>
) : MviProcessorHolder<CounterAction, CounterResult>() {

    override fun compose(observable: Observable<CounterAction>): Observable<CounterResult> {
        return Observable.merge(
            listOf(
                errorComposer(observable),
                generateGoalComposer(observable),
                updateValueComposer(observable)
            )
        )
    }

    override fun isAcceptAction(action: CounterAction): Boolean {
        return listOf(
            GenerateGoalAction::class.java,
            UpdateValueAction::class.java
        ).contains(action::class.java)
    }

    private fun generateGoalComposer(observable: Observable<CounterAction>): Observable<GenerateGoalResult> {
        return observable.ofType(GenerateGoalAction::class.java)
            .compose(generateGoalProcessor.execute())
    }

    private fun updateValueComposer(observable: Observable<CounterAction>): Observable<UpdateValueResult> {
        return observable.ofType(UpdateValueAction::class.java)
            .compose(updateValueProcessor.execute())
    }

}