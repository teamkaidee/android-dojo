package com.app.kaidee.endlessscroll.presentation.processor

import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.endlessscroll.data.SimpleItemRepository
import com.app.kaidee.endlessscroll.presentation.EndlessScrollAction.GetItemsAction
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult.GetItemsResult
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult.GetItemsResult.Error
import com.app.kaidee.endlessscroll.presentation.EndlessScrollResult.GetItemsResult.Success
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class GetItemsProcessor @Inject constructor(
	private val repository: SimpleItemRepository
) : MviProcessor<GetItemsAction, GetItemsResult> {

	override fun execute(): ObservableTransformer<GetItemsAction, GetItemsResult> {
		return ObservableTransformer { actions ->
			actions.flatMap { action ->
				repository.getItems(action.offset, LIMIT)
					.map { result ->
						Success(action.offset + 1, result)
					}
					.cast(GetItemsResult::class.java)
					.onErrorReturn { error ->
						Error(error)
					}
			}
		}
	}

	companion object {

		private const val LIMIT = 15

	}

}