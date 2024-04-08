plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.home.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:home:nav"))
    implementation(project(":features:clip:ui"))
    implementation(project(":features:quiz:ui"))
    implementation(project(":features:section:ui"))
    implementation(project(":theme"))
}
