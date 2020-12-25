import com.app.kaidee.configuration.ApplicationBuildVariant
import com.app.kaidee.configuration.ApplicationConfig
import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.dependencies.Dependencies.Module.Core
import com.app.kaidee.dependencies.Dependencies.Module.Feature
import com.app.kaidee.extension.appCompat
import com.app.kaidee.extension.dagger
import com.app.kaidee.extension.navigationComponent

plugins {
	id("com.android.application")
	kotlin("android")
	kotlin("android.extensions")
	kotlin("kapt")
	id("kaidee-application-config")
	id("kaidee-dependencies")
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

	buildFeatures {
		viewBinding = true
	}

	dynamicFeatures = mutableSetOf(Feature.counter, Feature.endlessScroll)
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	api(project(Core.common))
	api(project(Core.arch))
	implementation(Dependencies.App.kotlinJdk7)
	implementation(Dependencies.App.constraintLayout)
	implementation(Dependencies.App.viewModel)
	implementation(Dependencies.App.liveData)
	appCompat()
	navigationComponent()
	dagger()

	testImplementation(Dependencies.Test.junit)

	androidTestImplementation(Dependencies.AndroidTest.androidXJunit)
	androidTestImplementation(Dependencies.AndroidTest.espresso)
}
