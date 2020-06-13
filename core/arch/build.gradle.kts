plugins {
    id("common.android-library")
}

dependencies {
    implementation(project(Module.Core.common))
    api(Dependency.rxKotlin)
    api(Dependency.viewModel)
}
