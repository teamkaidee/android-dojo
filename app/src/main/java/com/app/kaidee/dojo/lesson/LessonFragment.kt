package com.app.kaidee.dojo.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.kaidee.dojo.R
import kotlinx.android.synthetic.main.fragment_lesson.*

class LessonFragment : Fragment() {

	private val lessonViewModel: LessonViewModel by viewModels()

	private val lessonListAdapter = LessonListAdapter()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_lesson, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupView()
		observeViewModel()
	}

	private fun setupView() {
		recycleview_lessons.layoutManager = LinearLayoutManager(context)
		recycleview_lessons.adapter = lessonListAdapter
		lessonListAdapter.onItemClickListener = { navigationId ->
			findNavController().navigate(navigationId)
		}
	}

	private fun observeViewModel() {
		lessonViewModel.getLessons().observe(viewLifecycleOwner, Observer { lessons ->
			lessonListAdapter.submitList(lessons)
		})
	}

}
