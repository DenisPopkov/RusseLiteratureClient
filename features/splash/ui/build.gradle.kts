plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.composesample.features.splash.ui"
}

dependencies {
    implementation(project(":theme"))
}