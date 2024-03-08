plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.fave.ui"
}

dependencies {
    implementation(project(":features:fave:domain"))
    implementation(project(":features:fave:nav"))
    implementation(project(":theme"))
}
