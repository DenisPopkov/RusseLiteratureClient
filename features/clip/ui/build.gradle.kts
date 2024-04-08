plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.clip.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:clip:nav"))
    implementation(project(":theme"))
}
