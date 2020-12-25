package com.app.kaidee.endlessscroll

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.dojo.App
import com.app.kaidee.endlessscroll.databinding.FragmentEndlessScrollBinding
import com.app.kaidee.endlessscroll.di.DaggerEndlessScrollComponent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent.InitialIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent.LoadMoreIntent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollPresenter
import javax.inject.Inject
import com.app.kaidee.endlessscroll.presentation.EndlessScrollIntent as Intent
import com.app.kaidee.endlessscroll.presentation.EndlessScrollViewState as ViewState

class EndlessScrollFragment : Fragment(R.layout.fragment_endless_scroll), MviView<Intent, ViewState> {

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
		with(FragmentEndlessScrollBinding.bind(view)) {
			setupRecyclerView(recycleviewSimpleData)
		}
		onScrollListener.disableLoadMore()
		presenter.dispatch(InitialIntent)
	}

	private fun setupRecyclerView(recyclerView: RecyclerView) {
		with(recyclerView){
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
			adapter = simpleDataListAdapter
			setHasFixedSize(true)
			onScrollListener = EndlessRecyclerOnScrollListener(layoutManager as LinearLayoutManager) {
				loadMore()
			}
			addOnScrollListener(onScrollListener)
		}
	}

	private fun loadMore() {
		onScrollListener.disableLoadMore()
		presenter.dispatch(LoadMoreIntent)
	}

	override fun render(state: ViewState) {
		onScrollListener.enableLoadMore()
		simpleDataListAdapter.submitList(state.items)
	}

}