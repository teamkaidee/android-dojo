package com.app.kaidee.dojo.lesson.repository

import com.app.kaidee.dojo.R
import com.app.kaidee.dojo.lesson.Lesson
import io.reactivex.Observable
import javax.inject.Inject

class LessonRepository @Inject constructor() {

    fun getLessons(): Observable<List<Lesson>> {
        return Observable.just(
            listOf(
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
        )
    }

}