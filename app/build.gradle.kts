import extension.appCompat
import extension.dagger
import extension.implementation
import extension.navigationComponent

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
		getByName(ApplicationBuildVariant.release) {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
	}

	dynamicFeatures = mutableSetOf(Module.Feature.counter, Module.Feature.endlessScroll)
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	api(project(Module.Core.common))
	api(project(Module.Core.arch))
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
