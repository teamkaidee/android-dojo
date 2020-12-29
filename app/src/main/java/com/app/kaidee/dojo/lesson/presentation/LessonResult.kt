package com.app.kaidee.dojo.lesson.presentation

import com.app.kaidee.arch.mvi.MviResult
import com.app.kaidee.dojo.lesson.Lesson

sealed class LessonResult : MviResult {

    sealed class LoadLessonResult : LessonResult() {

        object Loading : LoadLessonResult()

        data class Success(val lessons: List<Lesson>) : LoadLessonResult()

    }

}