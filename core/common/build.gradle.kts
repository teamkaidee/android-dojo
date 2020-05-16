plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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
    implementation(Dependency.kotlinJdk7)
    implementation(Dependency.rxKotlin)
    implementation(Dependency.rxAndroid)
    implementation(Dependency.viewModel)
    dagger()
}
