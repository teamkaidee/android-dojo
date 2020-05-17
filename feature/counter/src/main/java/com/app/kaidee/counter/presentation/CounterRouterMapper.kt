package com.app.kaidee.counter.presentation

import com.app.kaidee.counter.presentation.CounterRouter.*
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult
import javax.inject.Inject

class CounterRouterMapper @Inject constructor() {

    fun mapToRouter(result: CounterResult): CounterRouter {
        return when (result) {
            is UpdateValueResult.Win -> {
                ResultPage
            }
            else -> {
                Stay
            }
        }
    }

}