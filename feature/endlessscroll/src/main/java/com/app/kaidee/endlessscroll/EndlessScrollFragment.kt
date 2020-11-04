package com.app.kaidee.endlessscroll

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.dojo.App
import com.app.kaidee.endlessscroll.di.DaggerEndlessScrollComponent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent.InitialIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent.LoadMoreIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollPresenter
import com.app.kaidee.endlessscroll.presentation.EndlessScrollViewState
import kotlinx.android.synthetic.main.fragment_endless_scroll.*
import javax.inject.Inject

class EndlessScrollFragment : Fragment(), MviView<EndlessScrollIntent, EndlessScrollViewState> {

	@Inject
	lateinit var presenter: EndlessScrollPresenter

	private val simpleDataListAdapter = SimpleDataListAdapter()

	private lateinit var onScrollListener: EndlessRecyclerOnScrollListener

	@SuppressLint("CheckResult")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DaggerEndlessScrollComponent.builder()
			.appComponent(App.appComponent)
			.build()
			.inject(this)
		presenter.states().subscribe(::render) { e ->
			e.printStackTrace()
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_endless_scroll, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupRecyclerView()
		onScrollListener.disableLoadMore()
		presenter.dispatch(InitialIntent)
	}

	private fun setupRecyclerView() {
		val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		recycleview_simple_data?.layoutManager = layoutManager
		recycleview_simple_data?.adapter = simpleDataListAdapter
		recycleview_simple_data?.setHasFixedSize(true)
		onScrollListener = EndlessRecyclerOnScrollListener(layoutManager) {
			loadMore()
		}
		recycleview_simple_data?.addOnScrollListener(onScrollListener)
	}

	private fun loadMore() {
		onScrollListener.disableLoadMore()
		presenter.dispatch(LoadMoreIntent)
	}

	override fun render(state: EndlessScrollViewState) {
		onScrollListener.enableLoadMore()
		simpleDataListAdapter.submitList(state.items)
		recycleview_simple_data?.postDelayed({
			recycleview_simple_data?.smoothScrollToPosition(simpleDataListAdapter.itemCount - 1)
		}, 500)
	}

}