package com.app.kaidee.dojo.lesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaidee.arch.mvi.MviView
import com.app.kaidee.common.di.factory.ViewModelFactory
import com.app.kaidee.dojo.App
import com.app.kaidee.dojo.R
import com.app.kaidee.dojo.databinding.FragmentLessonBinding
import com.app.kaidee.dojo.di.component.DaggerLessonComponent
import com.app.kaidee.dojo.lesson.presentation.LessonIntent
import com.app.kaidee.dojo.lesson.presentation.LessonPresenter
import com.app.kaidee.dojo.lesson.presentation.LessonViewState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LessonFragment : Fragment(R.layout.fragment_lesson), MviView<LessonIntent, LessonViewState> {

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
        disposable.add(
            presenter.states().subscribe(this::render) { error ->
                println(error.message)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentLessonBinding.bind(view)) {
            with(recycleviewLessons) {
                layoutManager = LinearLayoutManager(context)
                adapter = lessonListAdapter
            }
        }
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