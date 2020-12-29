package com.app.kaidee.dependencies

object Dependencies {

    object Module {

        const val app = ":app"

        object Core {
            const val arch = ":core:arch"
            const val common = ":core:common"
        }

        object Feature {
            const val counter = ":feature:counter"
            const val endlessScroll = ":feature:endlessscroll"
        }

    }

    object Version {
        const val navigationComponent = "2.3.0-alpha06"
        const val androidLifecycle = "2.2.0"
        const val dagger = "2.27"
    }

    object App {
        const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val androidXCore = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigationComponent}"
        const val navUi = "androidx.navigation:navigation-ui-ktx:${Version.navigationComponent}"
        const val navFeature = "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigationComponent}"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.androidLifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.androidLifecycle}"
        const val dagger = "com.google.dagger:dagger:${Version.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val mockito = "org.mockito:mockito-inline:3.3.3"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    }

    object AndroidTest {
        const val androidXJunit = "androidx.test.ext:junit:1.1.1"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }

}