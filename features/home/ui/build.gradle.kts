plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.home.ui"
}

dependencies {
    implementation(project(":features:home:domain"))
    implementation(project(":features:home:nav"))
    implementation(project(":theme"))
}
