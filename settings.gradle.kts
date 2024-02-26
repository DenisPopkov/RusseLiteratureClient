@file:Suppress("UnstableApiUsage")

include(":features:splash:ui")


include(":features:splash:nav")


include(":features:splash")


pluginManagement {
    includeBuild("gradle-plugins")

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

rootProject.name = "RusseLiteratureClient"
include(
    ":app",
    ":core:feature:nav",
    ":core:feature:ui",
    ":features:library:data",
    ":features:library:domain",
    ":features:library:nav",
    ":features:library:ui",
    ":features:spotlight:nav",
    ":features:spotlight:ui",
    ":theme",
)
