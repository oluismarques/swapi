pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "starwars"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include (":app")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":presentation:launchpad")
