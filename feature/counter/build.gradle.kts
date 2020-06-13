import extension.appCompat
import extension.dagger
import extension.navigationComponent
import extension.unitTest

plugins {
    id("common.dynamic-feature")
}

dependencies {
    implementation(project(Module.Core.common))
    implementation(project(Module.Core.arch))
    implementation(Dependency.constraintLayout)
    appCompat()
    dagger()
    navigationComponent()

    unitTest()
}