package ru.popkov.russeliterature.features.auth.ui

sealed interface AuthViewAction {

    data object OnAlreadyHaveAccountClick : AuthViewAction
    data object OnNoAccountClick : AuthViewAction
    data class OnApplyPasswordClick(val password: String) : AuthViewAction
    data class OnApplyPhoneNumberClick(val phoneNumber: String) : AuthViewAction
}