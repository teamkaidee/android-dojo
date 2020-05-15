import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appCompat() {
    implementation(Dependency.appCompat)
    implementation(Dependency.androidXCore)
}

fun DependencyHandler.navigationComponent() {
    implementation(Dependency.navFragment)
    implementation(Dependency.navUi)
    implementation(Dependency.navFeature)
}

fun DependencyHandler.dagger() {
    implementation(Dependency.dagger)
    kapt(Dependency.daggerCompiler)
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}