package com.app.kaidee.counter.di.component

import com.app.kaidee.counter.CounterFragment
import com.app.kaidee.counter.di.module.CounterModule
import com.app.kaidee.counter.navigation.CounterNavigator
import com.app.kaidee.dojo.di.component.AppComponent
import com.app.kaidee.dojo.di.scope.Presentation
import dagger.BindsInstance
import dagger.Component

@Presentation
@Component(
	modules = [CounterModule::class],
	dependencies = [AppComponent::class]
)
interface CounterComponent {

	fun inject(fragment: CounterFragment)

	@Component.Builder
	interface Builder {

		fun appComponent(appComponent: AppComponent): Builder

		@BindsInstance
		fun navigator(navigator: CounterNavigator): Builder

		fun build(): CounterComponent

	}

}