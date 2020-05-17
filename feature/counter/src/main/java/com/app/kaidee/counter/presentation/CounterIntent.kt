package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviIntent

sealed class CounterIntent : MviIntent {

    object InitialIntent : CounterIntent()

    object IncreaseIntent : CounterIntent()

    object DecreaseIntent : CounterIntent()

}