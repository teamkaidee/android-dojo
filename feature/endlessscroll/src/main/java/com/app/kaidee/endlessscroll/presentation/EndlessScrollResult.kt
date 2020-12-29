package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviResult
import com.app.kaidee.endlessscroll.data.SimpleItem

sealed class EndlessScrollResult : MviResult {

    data class Success(val offset: Int, val items: List<SimpleItem>) : EndlessScrollResult()

    data class Error(val throwable: Throwable) : EndlessScrollResult()

}