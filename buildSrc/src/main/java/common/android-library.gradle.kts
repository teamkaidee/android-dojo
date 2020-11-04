package common

import ApplicationBuildVariant
import ApplicationConfig
import Dependency
import extension.implementation

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
		getByName(ApplicationBuildVariant.release) {
			isMinifyEnabled = false
		}
	}
}

dependencies {
	implementation(Dependency.kotlinJdk7)
}
