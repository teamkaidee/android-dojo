pluginManagement {
	repositories {
		google()
		jcenter()
		gradlePluginPortal()
	}
}

includeBuild("plugins")

include(":app")
include(":core:arch")
include(":core:common")
include(":feature:counter")
include(":feature:endlessscroll")