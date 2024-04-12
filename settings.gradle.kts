pluginManagement {
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

rootProject.name = "weldy"
include(":app")
include(":base")
include(":cat")
include(":cat:domain")
include(":cat:data-source")
include(":cat:framework")
include(":cat:ui")
include(":cat:interactor")
