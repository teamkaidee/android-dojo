package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviActionMapper
import com.app.kaidee.counter.presentation.CounterAction.GenerateGoalAction
import com.app.kaidee.counter.presentation.CounterAction.UpdateValueAction
import com.app.kaidee.counter.presentation.CounterIntent.*
import javax.inject.Inject

class CounterActionMapper @Inject constructor() : MviActionMapper<CounterIntent, CounterViewState, CounterAction> {

	override fun mapToAction(intent: CounterIntent, currentState: CounterViewState): CounterAction {
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