package com.app.kaidee.dojo.di.module

import androidx.lifecycle.ViewModel
import com.app.kaidee.common.di.scope.ViewModelKey
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.dojo.di.scope.Presentation
import com.app.kaidee.dojo.lesson.presentation.LessonPresenter
import com.app.kaidee.dojo.lesson.repository.LessonRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object LessonModule {

	@Provides
	@Presentation
	@IntoMap
	@ViewModelKey(LessonPresenter::class)
	fun providePresenter(schedulerProvider: SchedulerProvider, lessonRepository: LessonRepository): ViewModel {
		return LessonPresenter(schedulerProvider, lessonRepository)
	}

}