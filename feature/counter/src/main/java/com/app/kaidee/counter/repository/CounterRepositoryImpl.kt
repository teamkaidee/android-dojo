package com.app.kaidee.counter.repository

import javax.inject.Inject
import kotlin.random.Random

class CounterRepositoryImpl @Inject constructor() : CounterRepository {

    private var currentGoal = 0

    private var currentValue = 0

    override fun generateGameSession(): Pair<Int, Int> {
        currentValue = 0
        val from = 1
        val until = 5
        currentGoal = Random.nextInt(from, until)

        do {
            currentValue = Random.nextInt(from, until)
        } while (currentValue == currentGoal)

        return Pair(currentGoal, currentValue)
    }

    override fun isWin(value: Int): Boolean {
        currentValue += value
        return currentGoal == currentValue
    }

}