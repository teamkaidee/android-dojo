package com.app.kaidee.counter.presentation.processor

import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.counter.presentation.CounterAction.GenerateGoalAction
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult.*
import com.app.kaidee.counter.repository.CounterRepository
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class GenerateGoalProcessor @Inject constructor(
    private val counterRepository: CounterRepository
) : MviProcessor<GenerateGoalAction, GenerateGoalResult> {

    override fun execute(): ObservableTransformer<GenerateGoalAction, GenerateGoalResult> {
        return ObservableTransformer { actions ->
            actions.map {
                val (goal, startNumber) = counterRepository.generateGameSession()
                Success(goal, startNumber)
            }
                .cast(GenerateGoalResult::class.java)
                .onErrorReturn(::Failure)
                .startWith(Loading)
        }
    }

}