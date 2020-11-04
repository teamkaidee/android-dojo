import extension.appCompat
import extension.dagger
import extension.navigationComponent
import extension.unitTest

plugins {
	id("common.dynamic-feature")
}

dependencies {
	implementation(Dependency.constraintLayout)
	appCompat()
	dagger()
	navigationComponent()

	unitTest()
}