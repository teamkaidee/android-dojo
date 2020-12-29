package com.app.kaidee.dojo.lesson.presentation

import com.app.kaidee.arch.mvi.lite.MviLitePresenter
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.dojo.lesson.repository.LessonRepository
import io.reactivex.Observable
import javax.inject.Inject

class LessonPresenter @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    private val lessonRepository: LessonRepository
) : MviLitePresenter<LessonIntent, LessonViewState>(LessonViewState.idle(), schedulerProvider) {

    override fun processorHolder(intents: Observable<LessonIntent>): List<Observable<LessonViewState>> {
        return with(intents) {
            listOf(loadLessonProcessor())
        }
    }

    //region load-lesson
    private fun Observable<LessonIntent>.loadLessonProcessor(): Observable<LessonViewState> {
        return filterIsInstance<LessonIntent.InitialIntent>()
            .processIntent()
            .reduceState()
    }

    private fun Observable<LessonIntent.InitialIntent>.processIntent(): Observable<LessonResult.LoadLessonResult> {
        return flatMap {
            lessonRepository.getLessons()
                .map(LessonResult.LoadLessonResult::Success)
                .cast(LessonResult.LoadLessonResult::class.java)
                .startWith(LessonResult.LoadLessonResult.Loading)
        }
    }

    private fun Observable<LessonResult.LoadLessonResult>.reduceState(): Observable<LessonViewState> {
        return map { result ->
            when (result) {
                is LessonResult.LoadLessonResult.Loading -> setState {
                    copy(isLoading = true)
                }
                is LessonResult.LoadLessonResult.Success -> setState {
                    copy(
                        isLoading = false,
                        lessons = result.lessons,
                        error = null
                    )
                }
            }
        }
    }
    //endregion

}