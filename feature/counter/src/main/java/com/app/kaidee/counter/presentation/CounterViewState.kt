package com.app.kaidee.counter.presentation

import com.app.kaidee.arch.mvi.MviViewState

data class CounterViewState(
    val goal: Int,
    val count: Int,
    val isLoading: Boolean,
    val error: Throwable?
) : MviViewState {

    override fun toLogString(): String {
        return toString()
    }

    companion object {

        fun idle(): CounterViewState {
            return CounterViewState(
                goal = 0,
                count = 0,
                isLoading = true,
                error = null
            )
        }

    }

}