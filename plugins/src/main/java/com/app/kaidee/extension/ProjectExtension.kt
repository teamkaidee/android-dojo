package com.app.kaidee.extension

import com.android.build.gradle.BaseExtension
import com.app.kaidee.configuration.ApplicationConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun Project.setupAndroid() {
	extensions.configure(BaseExtension::class.java) {
		buildToolsVersion(ApplicationConfig.buildToolVersion)
		compileSdkVersion(ApplicationConfig.targetSdkVersion)
		defaultConfig {
			minSdkVersion(ApplicationConfig.minSdkVersion)
			targetSdkVersion(ApplicationConfig.targetSdkVersion)
		}
		compileOptions {
			sourceCompatibility = JavaVersion.VERSION_1_8
			targetCompatibility = JavaVersion.VERSION_1_8
		}
	}
}