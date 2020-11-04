package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviResult

sealed class CounterResult : MviResult {

	sealed class GenerateGoalResult : CounterResult() {

		object Loading : GenerateGoalResult()

		data class Success(val goal: Int, val startNumber: Int) : GenerateGoalResult()

		data class Failure(val throwable: Throwable) : GenerateGoalResult()

	}

	sealed class UpdateValueResult : CounterResult() {

		data class Win(val value: Int) : UpdateValueResult()

		data class Success(val value: Int) : UpdateValueResult()

		data class Failure(val throwable: Throwable) : UpdateValueResult()

	}

}