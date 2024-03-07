plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.library.ui"
}

dependencies {
    implementation(project(":features:library:domain"))
    implementation(project(":features:library:nav"))
    implementation(project(":theme"))
}
