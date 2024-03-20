plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.search.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:search:nav"))
    implementation(project(":theme"))
}
