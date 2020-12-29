package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviIntent

sealed class EndlessScrollIntent : MviIntent {

    object InitialIntent : EndlessScrollIntent()

    object LoadMoreIntent : EndlessScrollIntent()

}