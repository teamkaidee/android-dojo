import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.extension.dagger

plugins {
    id("kaidee-android-library")
}

dependencies {
    implementation(Dependencies.App.rxKotlin)
    implementation(Dependencies.App.rxAndroid)
    implementation(Dependencies.App.viewModel)
    dagger()
}
