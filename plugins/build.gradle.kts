plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
}

repositories {
	google()
	jcenter()
}

dependencies {
	implementation("com.android.tools.build:gradle:4.1.1")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
}

gradlePlugin {
	plugins.register("kaidee-dependencies") {
		id = "kaidee-dependencies"
		implementationClass = "com.app.kaidee.dependencies.DependenciesPlugin"
	}
	plugins.register("kaidee-application-config") {
		id = "kaidee-application-config"
		implementationClass = "com.app.kaidee.configuration.ApplicationConfigPlugin"
	}
	plugins.register("kaidee-android-library") {
		id = "kaidee-android-library"
		implementationClass = "com.app.kaidee.configuration.AndroidLibraryPlugin"
	}
	plugins.register("kaidee-android-feature") {
		id = "kaidee-android-feature"
		implementationClass = "com.app.kaidee.configuration.DynamicFeaturePlugin"
	}
}