plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.app.android.room)
}

android {
    namespace = "ru.popkov.russeliterature.features.auth.data"
}

dependencies {
    implementation(project(":features:auth:domain"))
    implementation(project(":core:feature:datastore"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.grpc)
    implementation(libs.bundles.datastore)
}
