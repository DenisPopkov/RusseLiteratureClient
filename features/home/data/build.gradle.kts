plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.app.android.room)
}

android {
    namespace = "ru.popkov.russeliterature.features.home.data"
}

dependencies {
    implementation(project(":features:home:domain"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
}
