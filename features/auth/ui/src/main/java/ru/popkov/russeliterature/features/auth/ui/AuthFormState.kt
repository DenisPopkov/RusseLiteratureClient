package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.runtime.Immutable

enum class AuthGlobalState {
    REGISTER_NEW_USER_PHONE_NUMBER,
    REGISTER_NEW_USER_PASSWORD,
    AUTH,
    ;
}

@Immutable
internal data class AuthFormState(
    val phoneNumber: String = "",
    val phoneNumberError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val authGlobalState: AuthGlobalState = AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER,
)