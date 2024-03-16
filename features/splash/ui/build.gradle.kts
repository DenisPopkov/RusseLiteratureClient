plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.splash.ui"
}

dependencies {
    implementation(project(":features:splash:nav"))
    implementation(project(":features:home:nav"))
    implementation(project(":features:auth:nav"))
    implementation(project(":core:feature:datastore"))
    implementation(project(":theme"))
}