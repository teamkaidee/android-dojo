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
}

tasks {
	val clean by registering(Delete::class) {
		delete(buildDir)
	}
}
