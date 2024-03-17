import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.app.feature.data)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "ru.popkov.android.core.feature.datastore"
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.47.0"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.bundles.datastore)
    api(libs.protobuf.kotlin.lite)
}