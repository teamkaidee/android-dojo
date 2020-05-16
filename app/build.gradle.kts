plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(ApplicationConfig.targetSdkVersion)
    buildToolsVersion(ApplicationConfig.buildToolVersion)
    defaultConfig {
        applicationId = ApplicationConfig.applicationId
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(BuildType.release) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dynamicFeatures = mutableSetOf(Module.Feature.counter)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Module.Core.common))
    implementation(Dependency.kotlinJdk7)
    implementation(Dependency.constraintLayout)
    implementation(Dependency.viewModel)
    implementation(Dependency.liveData)
    appCompat()
    navigationComponent()
    dagger()

    testImplementation(Dependency.junit)

    androidTestImplementation(Dependency.androidXJunit)
    androidTestImplementation(Dependency.espresso)
}
