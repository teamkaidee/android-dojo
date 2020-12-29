import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.dependencies.Dependencies.Module.Core

plugins {
    id("kaidee-android-library")
}

dependencies {
    implementation(project(Core.common))
    api(Dependencies.App.rxKotlin)
    api(Dependencies.App.viewModel)
}
