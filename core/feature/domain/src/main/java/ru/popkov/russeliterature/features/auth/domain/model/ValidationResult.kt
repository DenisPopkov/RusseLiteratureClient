package ru.popkov.russeliterature.features.auth.domain.model

data class ValidationResult(
    val successful: Boolean = false,
    val errorMessage: String? = null,
)