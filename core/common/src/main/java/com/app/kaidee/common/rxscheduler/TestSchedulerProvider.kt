package com.app.kaidee.common.rxscheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

	override fun io(): Scheduler {
		return Schedulers.trampoline()
	}

	override fun ui(): Scheduler {
		return Schedulers.trampoline()
	}

	override fun compute(): Scheduler {
		return Schedulers.trampoline()
	}

}