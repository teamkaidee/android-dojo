package com.app.kaidee.endlessscroll.data

import io.reactivex.Observable
import javax.inject.Inject

class SimpleItemRepository @Inject constructor() {

	private val simpleItems = mutableListOf<SimpleItem>()

	init {
		repeat(100) { index ->
			simpleItems.add(SimpleItem("Item : $index"))
		}
	}

	fun getItems(offset: Int, limit: Int): Observable<List<SimpleItem>> {
		val fromIndex = offset * limit
		val toIndex = fromIndex + limit
		return when {
			fromIndex >= simpleItems.size -> {
				Observable.just(emptyList())
			}
			toIndex >= simpleItems.size -> {
				Observable.just(simpleItems.subList(fromIndex, simpleItems.size))
			}
			else -> {
				Observable.just(simpleItems.subList(fromIndex, toIndex))
			}
		}
	}

}