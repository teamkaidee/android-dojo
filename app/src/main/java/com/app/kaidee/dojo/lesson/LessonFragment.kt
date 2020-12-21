package com.app.kaidee.dojo.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.common.di.factory.ViewModelFactory
import com.app.kaidee.dojo.App
import com.app.kaidee.dojo.R
import com.app.kaidee.dojo.di.component.DaggerLessonComponent
import com.app.kaidee.dojo.lesson.presentation.LessonIntent
import com.app.kaidee.dojo.lesson.presentation.LessonPresenter
import com.app.kaidee.dojo.lesson.presentation.LessonViewState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_lesson.*
import javax.inject.Inject

class LessonFragment : Fragment(), MviView<LessonIntent, LessonViewState> {

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val disposable = CompositeDisposable()

	private val presenter: LessonPresenter by viewModels {
		viewModelFactory
	}

	private val lessonListAdapter = LessonListAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DaggerLessonComponent.builder()
			.appComponent(App.appComponent)
			.build()
			.inject(this)
		disposable.add(presenter.states().subscribe(this::render) { error ->
			println(error.message)
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_lesson, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		recycleview_lessons.layoutManager = LinearLayoutManager(context)
		recycleview_lessons.adapter = lessonListAdapter
		lessonListAdapter.onItemClickListener = { navigationId ->
			findNavController().navigate(navigationId)
		}
		presenter.dispatch(LessonIntent.InitialIntent)
	}

	override fun render(state: LessonViewState) {
		if (state.error == null) {
			lessonListAdapter.submitList(state.lessons)
		}
	}

	override fun onDestroy() {
		disposable.dispose()
		super.onDestroy()
	}

}
