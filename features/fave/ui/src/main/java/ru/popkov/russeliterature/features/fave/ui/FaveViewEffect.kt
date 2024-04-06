package ru.popkov.russeliterature.features.fave.ui

internal sealed interface FaveViewEffect {
    data class ShowError(val errorMessage: String) : FaveViewEffect
    data object GoToMainScreen : FaveViewEffect

}