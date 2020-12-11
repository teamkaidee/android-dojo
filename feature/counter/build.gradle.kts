import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.extension.*
import org.gradle.kotlin.dsl.implementation

plugins {
	id("kaidee-android-feature")
}

dependencies {
	implementation(project(Dependencies.Module.app))
	implementation(Dependencies.App.constraintLayout)
	implementation(Dependencies.App.timber)
	appCompat()
	dagger()
	navigationComponent()

	unitTest()
}