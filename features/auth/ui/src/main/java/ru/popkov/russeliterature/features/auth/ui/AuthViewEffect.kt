package ru.popkov.russeliterature.features.auth.ui

internal sealed interface AuthViewEffect {

    data class ShowError(val errorMessage: String): AuthViewEffect
    data object ChangeAuthToAlreadyHaveAccount: AuthViewEffect
    data object ChangeAuthToNoAccount: AuthViewEffect
}