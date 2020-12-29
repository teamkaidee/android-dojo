package com.app.kaidee.endlessscroll

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessRecyclerOnScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0

    private var isLoading = true

    var visibleThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        checkEndless()
    }

    private fun checkEndless() {
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        if (isLoading && totalItemCount > previousTotal) {
            previousTotal = totalItemCount
        }

        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            onLoadMore()
        }
    }

    fun disableLoadMore() {
        isLoading = true
    }

    fun enableLoadMore() {
        isLoading = false
    }

}