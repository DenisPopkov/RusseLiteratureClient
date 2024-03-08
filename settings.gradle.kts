@file:Suppress("UnstableApiUsage")

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
    ":features:home:data",
    ":features:home:domain",
    ":features:home:nav",
    ":features:home:ui",
    ":features:search:data",
    ":features:search:domain",
    ":features:search:nav",
    ":features:search:ui",
    ":features:fave:data",
    ":features:fave:domain",
    ":features:fave:nav",
    ":features:fave:ui",
    ":features:auth:nav",
    ":features:auth:ui",
    ":features:auth:data",
    ":features:auth:domain",
    ":features:splash:nav",
    ":features:splash:ui",
    ":theme",
)
