package com.app.kaidee.dojo.di.module

import androidx.lifecycle.ViewModelProvider
import com.app.kaidee.common.di.factory.ViewModelFactory
import com.app.kaidee.common.rxscheduler.DefaultSchedulerProvider
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

	@Binds
	@Singleton
	abstract fun bindSchedulerProvider(impl: DefaultSchedulerProvider): SchedulerProvider

	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}