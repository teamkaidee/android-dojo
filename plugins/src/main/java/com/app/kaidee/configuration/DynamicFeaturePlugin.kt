package com.app.kaidee.configuration

import com.app.kaidee.extension.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class DynamicFeaturePlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("com.android.dynamic-feature")
				apply("kotlin-android")
				apply("kotlin-kapt")
			}
			setupAndroid(
				viewBinding = true
			)
		}
	}

}