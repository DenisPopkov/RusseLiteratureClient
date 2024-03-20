plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.app.android.room)
}

android {
    namespace = "ru.popkov.android.core.feature.data"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":core:feature:datastore"))
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.grpc)
    implementation(libs.bundles.datastore)
}
