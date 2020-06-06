package com.app.kaidee.counter.presentation.processor

import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.counter.presentation.CounterAction.GenerateGoalAction
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult.*
import com.app.kaidee.counter.repository.CounterRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class GenerateGoalProcessor @Inject constructor(
    private val generateGameSession: () -> Observable<Pair<Int, Int>>
) : MviProcessor<GenerateGoalAction, GenerateGoalResult> {

    override fun execute(): ObservableTransformer<GenerateGoalAction, GenerateGoalResult> {
        return ObservableTransformer { actions ->
            actions.flatMap {
                generateGameSession()
            }
                .map { (goal, startNumber) ->
                    Success(goal, startNumber)
                }
                .cast(GenerateGoalResult::class.java)
                .onErrorReturn(::Failure)
                .startWith(Loading)
        }
    }

}