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
        getByName(BuildType.release) {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Module.Core.common))
    implementation(Dependency.kotlinJdk7)
    api(Dependency.rxKotlin)
    api(Dependency.viewModel)
}
