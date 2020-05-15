plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(ApplicationConfig.targetSdkVersion)
    defaultConfig {
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Module.common))
    implementation(Dependency.kotlinJdk7)
    implementation(Dependency.rxKotlin)
    implementation(Dependency.viewModel)
}
