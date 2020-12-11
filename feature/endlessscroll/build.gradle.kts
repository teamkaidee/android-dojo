import com.app.kaidee.dependencies.Dependencies
import com.app.kaidee.extension.appCompat
import com.app.kaidee.extension.dagger
import com.app.kaidee.extension.navigationComponent
import com.app.kaidee.extension.unitTest

plugins {
	id("kaidee-android-feature")
}

dependencies {
	implementation(project(Dependencies.Module.app))
	implementation(Dependencies.App.constraintLayout)
	appCompat()
	dagger()
	navigationComponent()

	unitTest()
}