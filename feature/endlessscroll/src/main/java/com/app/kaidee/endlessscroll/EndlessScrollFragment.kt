package com.app.kaidee.endlessscroll

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.common.di.factory.ViewModelFactory
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
	lateinit var viewModelFactory: ViewModelFactory

	private val presenter by lazy {
		ViewModelProvider(this, viewModelFactory).get(EndlessScrollPresenter::class.java)
	}

	private val simpleDataListAdapter = SimpleDataListAdapter()

	private var onScrollListener: EndlessRecyclerOnScrollListener? = null

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

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(FragmentEndlessScrollBinding.bind(view)) {
			setupRecyclerView(recycleviewSimpleData)
		}
		load(InitialIntent)
	}

	private fun setupRecyclerView(recyclerView: RecyclerView) {
		with(recyclerView) {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
			adapter = simpleDataListAdapter
			setHasFixedSize(true)
			onScrollListener = EndlessRecyclerOnScrollListener(layoutManager as LinearLayoutManager) {
				load(LoadMoreIntent)
			}.also { listener ->
				addOnScrollListener(listener)
			}
		}
	}

	private fun load(intent: Intent) {
		onScrollListener?.disableLoadMore()
		presenter.dispatch(intent)
	}

	override fun render(state: ViewState) {
		onScrollListener?.enableLoadMore()
		simpleDataListAdapter.submitList(state.items)
	}

}