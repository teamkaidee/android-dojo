package com.app.kaidee.dojo.di.component

import com.app.kaidee.common.rxscheduler.SchedulerProvider
import com.app.kaidee.dojo.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [AppModule::class]
)
interface AppComponent {

	fun provideSchedulerProvider(): SchedulerProvider

}