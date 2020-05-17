package com.app.kaidee.counter.di.component

import com.app.kaidee.counter.CounterFragment
import com.app.kaidee.counter.di.module.CounterModule
import com.app.kaidee.dojo.di.component.AppComponent
import com.app.kaidee.dojo.di.scope.Presentation
import dagger.Component

@Presentation
@Component(
    modules = [CounterModule::class],
    dependencies = [AppComponent::class]
)
interface CounterComponent {

    fun inject(fragment: CounterFragment)

}