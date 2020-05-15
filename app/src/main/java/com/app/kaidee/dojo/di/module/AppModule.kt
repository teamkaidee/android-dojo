package com.app.kaidee.dojo.di.module

import com.app.kaidee.common.rxscheduler.DefaultSchedulerProvider
import com.app.kaidee.common.rxscheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return DefaultSchedulerProvider()
    }

}