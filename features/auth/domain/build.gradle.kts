import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.app.feature.domain)
    alias(libs.plugins.protobuf)
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
            task.plugins {
                create("grpc") {
                    option("lite")
                }
            }
        }
    }
}

android {
    namespace = "ru.popkov.russeliterature.features.auth.domain"
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.grpc)
    api(libs.protobuf.kotlin.lite)
}
