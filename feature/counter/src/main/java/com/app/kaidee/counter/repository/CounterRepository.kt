package com.app.kaidee.counter.repository

import javax.inject.Inject
import kotlin.random.Random

class CounterRepository @Inject constructor() {

	private var currentGoal = 0

	private var currentValue = 0

	fun generateGameSession(): Pair<Int, Int> {
		currentValue = 0
		currentGoal = Random.nextInt(1, 5)

		do {
			currentValue = Random.nextInt(1, 5)
		} while (currentValue == currentGoal)

		return Pair(currentGoal, currentValue)
	}

	fun isWin(value: Int): Boolean {
		currentValue += value
		return currentGoal == currentValue
	}

}