package com.app.kaidee.configuration

import com.android.build.gradle.BaseExtension
import com.app.kaidee.extension.implementation
import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.extension.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply {
				apply("com.android.library")
				apply("kotlin-android")
				apply("kotlin-kapt")
			}
			setupAndroid()
			extensions.configure(BaseExtension::class.java) {
				buildTypes {
					getByName(ApplicationBuildVariant.release) {
						isMinifyEnabled = true
					}
				}
			}
			with(dependencies) {
				implementation(Dependencies.App.kotlinJdk7)
			}
		}
	}

}