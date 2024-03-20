plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.android.compose)
    alias(libs.plugins.app.android.hilt)
}

android {
    namespace = "ru.popkov.russeliterature"

    defaultConfig {
        applicationId = "ru.popkov.russeliterature"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:feature:data"))
    implementation(project(":features:home:ui"))
    implementation(project(":features:search:ui"))
    implementation(project(":features:fave:ui"))
    implementation(project(":features:splash:ui"))
    implementation(project(":features:auth:ui"))
    implementation(project(":features:auth:nav"))
    implementation(project(":theme"))
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
