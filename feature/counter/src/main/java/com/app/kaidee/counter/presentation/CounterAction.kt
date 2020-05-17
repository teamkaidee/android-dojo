package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviAction

sealed class CounterAction : MviAction {

    object GenerateGoalAction : CounterAction()

    data class UpdateValueAction(val value: Int) : CounterAction()

}