package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviAction

sealed class EndlessScrollAction : MviAction {

	data class GetItemsAction(val offset: Int) : EndlessScrollAction()

}