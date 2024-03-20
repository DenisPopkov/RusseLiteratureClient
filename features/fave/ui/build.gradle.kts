plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.fave.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:fave:nav"))
    implementation(project(":theme"))
}
