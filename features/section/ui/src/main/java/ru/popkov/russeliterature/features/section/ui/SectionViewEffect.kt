package ru.popkov.russeliterature.features.section.ui

internal sealed interface SectionViewEffect {

    data object OnBackClick : SectionViewEffect
    data class ShowError(val errorMessage: String) : SectionViewEffect

    data class OnCardClick(val cardId: Long) : SectionViewEffect
}