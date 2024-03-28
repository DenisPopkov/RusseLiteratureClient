plugins {
    alias(libs.plugins.app.feature.ui)
}

android {
    namespace = "ru.popkov.russeliterature.features.quiz.ui"
}

dependencies {
    implementation(project(":core:feature:domain"))
    implementation(project(":features:quiz:nav"))
    implementation(project(":theme"))
}
