package com.app.kaidee.dojo.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.kaidee.dojo.R
import javax.inject.Inject

class LessonViewModel @Inject constructor() : ViewModel() {

	private val lessonsLiveData = MutableLiveData<List<Lesson>>()

	init {
		lessonsLiveData.postValue(lessons())
	}

	fun getLessons(): LiveData<List<Lesson>> {
		return lessonsLiveData
	}

	private fun lessons(): List<Lesson> {
		return listOf(
			Lesson(
				title = "MVI Counter Example",
				description = "Introduce basic concept of MVI Architecture Pattern",
				navigationId = R.id.action_lesson_to_counter
			),
			Lesson(
				title = "MVI Endless Scroll Example",
				description = "Prove of concept for how endless scroll working with MVI Architecture Pattern",
				navigationId = R.id.action_lesson_to_endless_scroll
			)
		)
	}

}