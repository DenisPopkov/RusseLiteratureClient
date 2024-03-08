package ru.popkov.russeliterature.features.auth.ui

internal sealed interface AuthViewEffect {

    data object ShowError: AuthViewEffect
    data object ValidateField: AuthViewEffect
    data object ChangeAuthToAlreadyHaveAccount: AuthViewEffect
    data object ChangeAuthToNoAccount: AuthViewEffect
}