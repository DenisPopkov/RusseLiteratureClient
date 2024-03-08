package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class AuthFormState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)