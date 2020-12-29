package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviReducer
import com.app.kaidee.arch.mvi.MviReducerHolder
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult
import javax.inject.Inject

class CounterReducerHolder @Inject constructor(
    private val generateGoalReducer: MviReducer<GenerateGoalResult, CounterViewState>,
    private val updateValueReducer: MviReducer<UpdateValueResult, CounterViewState>
) : MviReducerHolder<CounterResult, CounterViewState> {

    override fun apply(previousState: CounterViewState, result: CounterResult): CounterViewState {
        return when (result) {
            is GenerateGoalResult -> {
                generateGoalReducer.reduce(result, previousState)
            }
            is UpdateValueResult -> {
                updateValueReducer.reduce(result, previousState)
            }
        }
    }

}