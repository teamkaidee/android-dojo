package com.app.kaidee.counter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.common.di.factory.ViewModelFactory
import com.app.kaidee.counter.databinding.FragmentCounterBinding
import com.app.kaidee.counter.di.component.DaggerCounterComponent
import com.app.kaidee.counter.presentation.CounterIntent
import com.app.kaidee.counter.presentation.CounterIntent.*
import com.app.kaidee.counter.presentation.CounterPresenter
import com.app.kaidee.counter.presentation.CounterRouter
import com.app.kaidee.counter.presentation.CounterViewState
import com.app.kaidee.dojo.App
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import com.app.kaidee.dojo.R as appRes

class CounterFragment : Fragment(R.layout.fragment_counter), MviView<CounterIntent, CounterViewState> {

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val disposable = CompositeDisposable()

	private val presenter by lazy {
		ViewModelProvider(this, viewModelFactory).get(CounterPresenter::class.java)
	}

	private var binding: FragmentCounterBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DaggerCounterComponent.builder()
			.appComponent(App.appComponent)
			.build()
			.inject(this)
		disposable.add(presenter.states().subscribe(::render, ::handleError))
		disposable.add(presenter.navigation().subscribe(::route, ::handleError))
		presenter.dispatch(InitialIntent)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentCounterBinding.bind(view)
		setupView()
	}

	override fun onDestroyView() {
		binding = null
		super.onDestroyView()
	}

	private fun setupView() {
		bind {
			groupContent.referencedIds = intArrayOf(
				R.id.textview_goal,
				R.id.textview_count,
				R.id.button_decrease,
				R.id.button_increase
			)
			buttonDecrease.setOnClickListener {
				presenter.dispatch(DecreaseIntent)
			}
			buttonIncrease.setOnClickListener {
				presenter.dispatch(IncreaseIntent)
			}
			buttonLesson.setOnClickListener {
				route(CounterRouter.Lesson)
			}
		}
	}

	override fun render(state: CounterViewState) {
		bind {
			when {
				state.isLoading -> {
					progressbar.show()
					groupContent.visibility = View.GONE
				}
				state.error != null -> {
					progressbar.hide()
					handleError(state.error)
					groupContent.visibility = View.GONE
				}
				else -> {
					progressbar.hide()
					groupContent.visibility = View.VISIBLE
					textviewGoal.text = String.format(getString(R.string.counter_goal), state.goal)
					textviewCount.text = state.count.toString()
				}
			}
		}
	}

	private fun handleError(throwable: Throwable) {
		throwable.printStackTrace()
	}

	private fun route(router: CounterRouter) {
		when (router) {
			is CounterRouter.ResultPage -> {
				findNavController().navigate(appRes.id.action_counter_to_result)
			}
			is CounterRouter.Lesson -> {
				findNavController().popBackStack()
			}
			else -> Unit
		}
	}

	private fun bind(binder: FragmentCounterBinding.() -> Unit) {
		binding?.let { binding ->
			binder(binding)
		}
	}

}
