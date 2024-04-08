plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.auth.ui"
}

dependencies {
    implementation(project(":features:auth:nav"))
    implementation(project(":core:feature:domain"))
    implementation(project(":features:home:nav"))
    implementation(project(":theme"))
    implementation(libs.bundles.datastore)
}