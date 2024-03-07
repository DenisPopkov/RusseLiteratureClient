plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.auth.ui"
}

dependencies {
    implementation(project(":features:auth:nav"))
    implementation(project(":features:auth:domain"))
    implementation(project(":features:spotlight:nav"))
    implementation(project(":theme"))
}