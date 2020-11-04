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
