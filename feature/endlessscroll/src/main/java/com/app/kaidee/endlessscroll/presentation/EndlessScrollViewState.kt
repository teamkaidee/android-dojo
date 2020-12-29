package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviViewState
import com.app.kaidee.endlessscroll.data.SimpleItem

data class EndlessScrollViewState(
    val items: List<SimpleItem>,
    val offset: Int,
    val error: Throwable?
) : MviViewState {

    override fun toLogString(): String {
        return toString()
    }

    companion object {

        fun idle(): EndlessScrollViewState {
            return EndlessScrollViewState(
                items = mutableListOf(),
                offset = 0,
                error = null
            )
        }

    }

}