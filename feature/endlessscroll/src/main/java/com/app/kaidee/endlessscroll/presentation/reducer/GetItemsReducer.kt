package com.app.kaidee.endlessscroll.presentation.reducer

import com.app.kaidee.arch.mvi.MviReducer
import com.app.kaidee.endlessscroll.data.SimpleItem
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult
import com.app.kaidee.endlessscroll.presentation.EndlessScrollViewState
import javax.inject.Inject

class GetItemsReducer @Inject constructor() : MviReducer<EndlessScrollResult.GetItemsResult, EndlessScrollViewState> {

	override fun reduce(
		result: EndlessScrollResult.GetItemsResult,
		previousState: EndlessScrollViewState
	): EndlessScrollViewState {
		return when (result) {
			is EndlessScrollResult.GetItemsResult.Success -> {
				val newItems = mutableListOf<SimpleItem>().apply {
					addAll(previousState.items)
					addAll(result.items)
				}
				previousState.copy(
					offset = result.offset,
					items = newItems,
					error = null
				)
			}
			is EndlessScrollResult.GetItemsResult.Error -> {
				previousState.copy(
					offset = 0,
					items = emptyList(),
					error = result.throwable
				)
			}
		}
	}

}