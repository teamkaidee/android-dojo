plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        disabledRules.set(setOf("no-wildcard-imports", "no-blank-line-before-rbrace"))
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
