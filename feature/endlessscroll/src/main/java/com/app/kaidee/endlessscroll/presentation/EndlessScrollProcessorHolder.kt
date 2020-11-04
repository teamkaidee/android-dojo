package com.app.kaidee.endlessscroll.presentation

import com.app.kaidee.arch.mvi.MviProcessorHolder
import com.app.kaidee.endlessscroll.presentation.processor.GetItemsProcessor
import io.reactivex.Observable
import javax.inject.Inject

class EndlessScrollProcessorHolder @Inject constructor(
	private val getItemsProcessor: GetItemsProcessor
) : MviProcessorHolder<EndlessScrollAction, EndlessScrollResult>() {

	override fun compose(observable: Observable<EndlessScrollAction>): Observable<EndlessScrollResult> {
		return Observable.merge(
			listOf(
				getItemsComposer(observable),
				errorComposer(observable)
			)
		)
	}

	override fun isAcceptAction(action: EndlessScrollAction): Boolean {
		return action is EndlessScrollAction.GetItemsAction
	}

	private fun getItemsComposer(
		observable: Observable<EndlessScrollAction>
	): Observable<EndlessScrollResult.GetItemsResult> {
		return observable.ofType(EndlessScrollAction.GetItemsAction::class.java)
			.compose(getItemsProcessor.execute())
	}

}