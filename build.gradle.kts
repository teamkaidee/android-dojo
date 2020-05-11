buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugin.gradleBuildTool)
        classpath(Plugin.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
