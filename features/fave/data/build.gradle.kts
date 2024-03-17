plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.app.android.room)
}

android {
    namespace = "ru.popkov.russeliterature.features.fave.data"
}

dependencies {
    implementation(project(":features:fave:domain"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
}
