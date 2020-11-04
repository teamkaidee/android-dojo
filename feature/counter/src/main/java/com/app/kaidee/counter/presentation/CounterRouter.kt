package com.app.kaidee.counter.presentation

sealed class CounterRouter {

	object Stay : CounterRouter()

	object ResultPage : CounterRouter()

	object Lesson : CounterRouter()

}