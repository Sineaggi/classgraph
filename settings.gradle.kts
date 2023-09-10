dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "classgraph"

include("blackbox-test")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
