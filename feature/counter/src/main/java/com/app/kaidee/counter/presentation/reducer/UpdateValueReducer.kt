package com.app.kaidee.counter.presentation.reducer

import com.app.kaidee.arch.mvi.MviReducer
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult.*
import com.app.kaidee.counter.presentation.CounterViewState
import javax.inject.Inject

class UpdateValueReducer @Inject constructor() : MviReducer<UpdateValueResult, CounterViewState> {

    override fun reduce(result: UpdateValueResult, previousState: CounterViewState): CounterViewState {
        return when (result) {
            is Win -> {
                previousState.copy(
                    count = previousState.count + result.value,
                    error = null
                )
            }
            is Success -> {
                previousState.copy(
                    count = previousState.count + result.value,
                    error = null
                )
            }
            is Failure -> {
                previousState.copy(
                    error = result.throwable
                )
            }
        }
    }

}