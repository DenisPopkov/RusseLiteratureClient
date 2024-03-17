package ru.popkov.russeliterature.features.auth.ui

sealed interface AuthViewAction {

    data object OnAlreadyHaveAccountClick : AuthViewAction
    data object OnNoAccountClick : AuthViewAction
    data object OnDone : AuthViewAction
    data class OnPasswordChange(val password: String) : AuthViewAction
    data class OnPhoneNumberChange(val phoneNumber: String) : AuthViewAction
}