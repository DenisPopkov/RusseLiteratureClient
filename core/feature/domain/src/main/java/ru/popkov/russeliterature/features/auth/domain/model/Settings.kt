package ru.popkov.russeliterature.features.auth.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class Settings(
    val name: String,
    val image: String,
)