plugins {
    id("com.android.dynamic-feature")
}

android {
    compileSdkVersion(ApplicationConfig.targetSdkVersion)
    buildToolsVersion(ApplicationConfig.buildToolVersion)
    defaultConfig {
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
    }
}

dependencies {
    implementation(project(Module.app))
}