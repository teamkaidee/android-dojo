import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependency {

    // App
    const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val androidXCore = "androidx.core:core-ktx:${Version.androidX}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigationComponent}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Version.navigationComponent}"
    const val navFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigationComponent}"

    // Unit Test
    const val junit = "junit:junit:${Version.junit}"

    // Android Test
    const val androidXJunit = "androidx.test.ext:junit:${Version.androidXJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"

}