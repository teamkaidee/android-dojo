package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviActionMapper
import javax.inject.Inject

class EndlessScrollActionMapper @Inject constructor() :
	MviActionMapper<EndlessScrollIntent, EndlessScrollViewState, EndlessScrollAction> {

	override fun mapToAction(intent: EndlessScrollIntent, currentState: EndlessScrollViewState): EndlessScrollAction {
		return when (intent) {
			is EndlessScrollIntent.InitialIntent -> {
				EndlessScrollAction.GetItemsAction(0)
			}
			is EndlessScrollIntent.LoadMoreIntent -> {
				EndlessScrollAction.GetItemsAction(currentState.offset)
			}
		}
	}

}