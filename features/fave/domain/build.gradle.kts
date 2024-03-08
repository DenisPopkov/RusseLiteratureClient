plugins {
    alias(libs.plugins.app.feature.domain)
}

android {
    namespace = "ru.popkov.russeliterature.features.fave.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
}
