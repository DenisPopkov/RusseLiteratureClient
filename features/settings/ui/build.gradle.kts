plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.settings.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:settings:nav"))
    implementation(project(":features:auth:nav"))
    implementation(project(":theme"))
}
