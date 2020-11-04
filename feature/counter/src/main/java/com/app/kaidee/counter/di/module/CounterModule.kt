package com.app.kaidee.counter.di.module

import androidx.lifecycle.ViewModel
import com.app.kaidee.arch.mvi.MviProcessor
import com.app.kaidee.arch.mvi.MviReducer
import com.app.kaidee.common.di.scope.ViewModelKey
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.counter.presentation.*
import com.app.kaidee.counter.presentation.CounterAction.GenerateGoalAction
import com.app.kaidee.counter.presentation.CounterAction.UpdateValueAction
import com.app.kaidee.counter.presentation.CounterResult.GenerateGoalResult
import com.app.kaidee.counter.presentation.CounterResult.UpdateValueResult
import com.app.kaidee.counter.presentation.processor.GenerateGoalProcessor
import com.app.kaidee.counter.presentation.processor.UpdateValueProcessor
import com.app.kaidee.counter.presentation.reducer.GenerateGoalReducer
import com.app.kaidee.counter.presentation.reducer.UpdateValueReducer
import com.app.kaidee.counter.repository.CounterRepository
import com.app.kaidee.counter.repository.CounterRepositoryImpl
import com.app.kaidee.counter.usecase.CheckIsWin
import com.app.kaidee.counter.usecase.GenerateGameSession
import com.app.kaidee.dojo.di.scope.Presentation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CounterModule {

	@Binds
	@Presentation
	abstract fun bindCounterRepository(impl: CounterRepositoryImpl): CounterRepository

	@Binds
	abstract fun bindGenerateGoalReducer(impl: GenerateGoalReducer): MviReducer<GenerateGoalResult, CounterViewState>

	@Binds
	abstract fun bindUpdateValueReducer(impl: UpdateValueReducer): MviReducer<UpdateValueResult, CounterViewState>

	@Module
	companion object {

		@Provides
		fun provideGenerateGoalProcessor(
        generateGameSession: GenerateGameSession
    ): MviProcessor<GenerateGoalAction, GenerateGoalResult> {
			return GenerateGoalProcessor(generateGameSession)
		}

		@Provides
		fun provideUpdateValueProcessor(
        checkIsWin: CheckIsWin
    ): MviProcessor<UpdateValueAction, UpdateValueResult> {
			return UpdateValueProcessor(checkIsWin)
		}

		@Provides
		@Presentation
		@IntoMap
		@ViewModelKey(CounterPresenter::class)
		fun provideCounterPresenter(
        schedulerProvider: SchedulerProvider,
        processorHolder: CounterProcessorHolder,
        reducerHolder: CounterReducerHolder,
        actionMapper: CounterActionMapper,
        routerMapper: CounterRouterMapper
    ): ViewModel {
			return CounterPresenter(
          initialState = CounterViewState.idle(),
          schedulerProvider = schedulerProvider,
          processorHolder = processorHolder,
          reducerHolder = reducerHolder,
          actionMapper = actionMapper,
          routerMapper = routerMapper
      )
		}

	}

}