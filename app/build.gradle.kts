plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependency.kotlinJdk7)
    implementation(Dependency.constraintLayout)
    appCompat()
    navigationComponent()

    // Unit Test
    testImplementation(Dependency.junit)

    // UI Test
    androidTestImplementation(Dependency.androidXJunit)
    androidTestImplementation(Dependency.espresso)
}
