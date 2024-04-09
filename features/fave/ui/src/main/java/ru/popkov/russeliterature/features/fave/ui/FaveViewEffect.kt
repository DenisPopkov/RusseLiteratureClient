package ru.popkov.russeliterature.features.fave.ui

internal sealed interface FaveViewEffect {
    data class ShowError(val errorMessage: String) : FaveViewEffect
    data class OnSectionClick(val sectionId: Int) : FaveViewEffect
    data object GoToMainScreen : FaveViewEffect

    data class OnCardClick(val cardId: Long) : FaveViewEffect
}