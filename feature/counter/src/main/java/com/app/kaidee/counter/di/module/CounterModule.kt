package com.app.kaidee.counter.di.module

import androidx.lifecycle.ViewModel
import com.app.kaidee.common.di.scope.ViewModelKey
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.counter.navigation.CounterNavigator
import com.app.kaidee.counter.presentation.CounterPresenter
import com.app.kaidee.counter.presentation.CounterViewState
import com.app.kaidee.counter.repository.CounterRepository
import com.app.kaidee.counter.usecase.CheckIsWin
import com.app.kaidee.counter.usecase.GenerateGameSession
import com.app.kaidee.dojo.di.scope.Presentation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object CounterModule {

	@Provides
	@Presentation
	fun provideCounterRepository(): CounterRepository {
		return CounterRepository()
	}

	@Provides
	@Presentation
	@IntoMap
	@ViewModelKey(CounterPresenter::class)
	fun provideCounterPresenter(
		schedulerProvider: SchedulerProvider,
		generateGameSession: GenerateGameSession,
		checkIsWin: CheckIsWin,
		navigator: CounterNavigator
	): ViewModel {
		return CounterPresenter(
			initialState = CounterViewState.idle(),
			schedulerProvider = schedulerProvider,
			generateGameSession = generateGameSession,
			checkIsWin = checkIsWin,
			navigator = navigator
		)
	}

}