package com.app.kaidee.dojo.lesson.presentation

import com.app.kaidee.arch.mvi.MviIntent

sealed class LessonIntent : MviIntent {

    object InitialIntent : LessonIntent()

}