package com.app.kaidee.dojo

import android.app.Application
import com.app.kaidee.dojo.di.component.AppComponent
import com.app.kaidee.dojo.di.component.DaggerAppComponent

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder().build()
	}

	companion object {

		lateinit var appComponent: AppComponent

	}

}