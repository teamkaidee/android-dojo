package com.app.kaidee.endlessscroll.di

import androidx.lifecycle.ViewModel
import com.app.kaidee.common.di.scope.ViewModelKey
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.dojo.di.scope.Presentation
import com.app.kaidee.endlessscroll.data.SimpleItemRepository
import com.app.kaidee.endlessscroll.presentation.EndlessScrollPresenter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object EndlessScrollModule {

    @Provides
    @Presentation
    @IntoMap
    @ViewModelKey(EndlessScrollPresenter::class)
    fun providePresenter(schedulerProvider: SchedulerProvider, repository: SimpleItemRepository): ViewModel {
        return EndlessScrollPresenter(schedulerProvider, repository)
    }

}