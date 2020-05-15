package com.app.kaidee.common.rxscheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun compute(): Scheduler

}