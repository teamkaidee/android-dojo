package com.app.kaidee.dojo.di.component

import com.app.kaidee.dojo.di.module.LessonModule
import com.app.kaidee.dojo.di.scope.Presentation
import com.app.kaidee.dojo.lesson.LessonFragment
import dagger.Component

@Presentation
@Component(
    dependencies = [AppComponent::class],
    modules = [LessonModule::class]
)
interface LessonComponent {

    fun inject(fragment: LessonFragment)

}