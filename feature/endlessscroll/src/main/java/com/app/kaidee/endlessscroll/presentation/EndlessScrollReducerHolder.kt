package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviReducerHolder
import com.app.kaidee.endlessscroll.presentation.reducer.GetItemsReducer
import javax.inject.Inject

class EndlessScrollReducerHolder @Inject constructor(
	private val getItemsReducer: GetItemsReducer
) : MviReducerHolder<EndlessScrollResult, EndlessScrollViewState> {

	override fun apply(previousState: EndlessScrollViewState, result: EndlessScrollResult): EndlessScrollViewState {
		return when (result) {
			is EndlessScrollResult.GetItemsResult -> {
				getItemsReducer.reduce(result, previousState)
			}
		}
	}

}