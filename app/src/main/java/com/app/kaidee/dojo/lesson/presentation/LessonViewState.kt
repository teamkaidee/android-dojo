package com.app.kaidee.dojo.lesson.presentation

import com.app.kaidee.arch.mvi.MviViewState
import com.app.kaidee.dojo.lesson.Lesson

data class LessonViewState(
    val isLoading: Boolean,
    val error: Throwable?,
    val lessons: List<Lesson>
) : MviViewState {

    override fun toLogString(): String {
        return StringBuilder()
            .append("isLoading : $isLoading | ")
            .append("error : ${error?.message} | ")
            .append("lessons.size : ${lessons.size}")
            .toString()
    }

    companion object {

        fun idle(): LessonViewState {
            return LessonViewState(
                isLoading = true,
                error = null,
                lessons = listOf()
            )
        }
    }

}