import extension.dagger
import extension.implementation

plugins {
    id("common.android-library")
    kotlin("kapt")
}

dependencies {
    implementation(Dependency.rxKotlin)
    implementation(Dependency.rxAndroid)
    implementation(Dependency.viewModel)
    dagger()
}
