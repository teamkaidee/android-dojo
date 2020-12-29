package com.app.kaidee.counter.repository

interface CounterRepository {

    fun generateGameSession(): Pair<Int, Int>

    fun isWin(value: Int): Boolean

}