package com.app.kaidee.counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.common.di.factory.ViewModelFactory
import com.app.kaidee.counter.di.component.DaggerCounterComponent
import com.app.kaidee.counter.presentation.CounterIntent
import com.app.kaidee.counter.presentation.CounterIntent.*
import com.app.kaidee.counter.presentation.CounterPresenter
import com.app.kaidee.counter.presentation.CounterRouter
import com.app.kaidee.counter.presentation.CounterViewState
import com.app.kaidee.dojo.App
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_counter.*
import com.app.kaidee.dojo.R as appRes
import javax.inject.Inject

class CounterFragment : Fragment(), MviView<CounterIntent, CounterViewState> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val disposable = CompositeDisposable()

    private val presenter by lazy {
        ViewModelProvider(this, viewModelFactory).get(CounterPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCounterComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        disposable.add(presenter.states().subscribe(::render, ::handleError))
        disposable.add(presenter.navigation().subscribe(::route, ::handleError))
    }

    private fun setupView() {
        group_content?.referencedIds = intArrayOf(
            R.id.textview_goal,
            R.id.textview_count,
            R.id.button_decrease,
            R.id.button_increase
        )
        button_decrease?.setOnClickListener {
            presenter.dispatch(DecreaseIntent)
        }
        button_increase?.setOnClickListener {
            presenter.dispatch(IncreaseIntent)
        }
        button_lesson?.setOnClickListener {
            route(CounterRouter.Lesson)
        }
    }

    override fun render(state: CounterViewState) {
        when {
            state.isLoading -> {
                progressbar?.show()
                group_content?.visibility = View.GONE
            }
            state.error != null -> {
                progressbar?.hide()
                handleError(state.error)
                group_content?.visibility = View.GONE
            }
            else -> {
                progressbar?.hide()
                group_content?.visibility = View.VISIBLE
                textview_goal?.text = String.format(getString(R.string.counter_goal), state.goal)
                textview_count?.text = state.count.toString()
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
        }
    }

}
