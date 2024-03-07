plugins {
    alias(libs.plugins.app.feature.domain)
}

android {
    namespace = "ru.popkov.russeliterature.features.auth.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
}
