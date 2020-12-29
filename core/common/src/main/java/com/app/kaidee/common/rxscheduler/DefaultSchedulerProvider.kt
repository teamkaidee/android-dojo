package com.app.kaidee.common.rxscheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun compute(): Scheduler {
        return Schedulers.computation()
    }

}