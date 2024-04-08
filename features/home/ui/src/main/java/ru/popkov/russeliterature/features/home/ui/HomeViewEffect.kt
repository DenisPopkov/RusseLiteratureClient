package ru.popkov.russeliterature.features.home.ui

internal sealed interface HomeViewEffect {

    data class ShowError(val errorMessage: String) : HomeViewEffect
    data class OnSectionClick(val sectionId: Int) : HomeViewEffect
    data class OnCardClick(val cardId: Long) : HomeViewEffect
}