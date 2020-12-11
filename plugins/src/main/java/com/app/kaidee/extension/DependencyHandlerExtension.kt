package com.app.kaidee.extension

import com.app.kaidee.dependencies.Dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appCompat() {
	implementation(Dependencies.App.appCompat)
	implementation(Dependencies.App.androidXCore)
}

fun DependencyHandler.navigationComponent() {
	implementation(Dependencies.App.navFragment)
	implementation(Dependencies.App.navUi)
	implementation(Dependencies.App.navFeature)
}

fun DependencyHandler.dagger() {
	implementation(Dependencies.App.dagger)
	kapt(Dependencies.App.daggerCompiler)
}

fun DependencyHandler.unitTest() {
	testImplementation(Dependencies.Test.junit)
	testImplementation(Dependencies.Test.mockito)
	testImplementation(Dependencies.Test.mockitoKotlin)
}

fun DependencyHandler.implementation(depName: String) {
	add("implementation", depName)
}

fun DependencyHandler.kapt(depName: String) {
	add("kapt", depName)
}

fun DependencyHandler.compileOnly(depName: String) {
	add("compileOnly", depName)
}

fun DependencyHandler.api(depName: String) {
	add("api", depName)
}

fun DependencyHandler.testImplementation(depName: String) {
	add("testImplementation", depName)
}