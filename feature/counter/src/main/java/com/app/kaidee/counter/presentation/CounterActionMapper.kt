package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviActionMapper
import com.app.kaidee.counter.presentation.CounterIntent.*
import com.app.kaidee.counter.presentation.CounterAction.*
import javax.inject.Inject

class CounterActionMapper @Inject constructor() : MviActionMapper<CounterIntent, CounterAction> {

    override fun mapToAction(intent: CounterIntent): CounterAction {
        return when (intent) {
            is InitialIntent -> {
                GenerateGoalAction
            }
            is IncreaseIntent -> {
                UpdateValueAction(1)
            }
            is DecreaseIntent -> {
                UpdateValueAction(-1)
            }
        }
    }

}