package com.app.kaidee.common.rxscheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DefaultSchedulerProvider: SchedulerProvider {

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