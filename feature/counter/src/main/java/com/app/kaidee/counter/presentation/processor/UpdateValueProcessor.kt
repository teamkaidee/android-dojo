package com.app.kaidee.counter.presentation.processor

import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.counter.presentation.CounterAction.UpdateValueAction
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult.*
import com.app.kaidee.counter.repository.CounterRepository
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class UpdateValueProcessor @Inject constructor(
    private val counterRepository: CounterRepository
) : MviProcessor<UpdateValueAction, UpdateValueResult> {

    override fun execute(): ObservableTransformer<UpdateValueAction, UpdateValueResult> {
        return ObservableTransformer { actions ->
            actions.map { action ->
                if (counterRepository.isWin(action.value)) {
                    Win(action.value)
                } else {
                    Success(action.value)
                }
            }
                .cast(UpdateValueResult::class.java)
                .onErrorReturn(::Failure)
        }
    }

}