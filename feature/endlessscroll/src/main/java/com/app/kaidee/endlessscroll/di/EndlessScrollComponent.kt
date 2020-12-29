package com.app.kaidee.endlessscroll.di

import com.app.kaidee.dojo.di.component.AppComponent
import com.app.kaidee.dojo.di.scope.Presentation
import com.app.kaidee.endlessscroll.EndlessScrollFragment
import dagger.Component

@Presentation
@Component(
    dependencies = [AppComponent::class],
    modules = [EndlessScrollModule::class]
)
interface EndlessScrollComponent {

    fun inject(fragment: EndlessScrollFragment)

}