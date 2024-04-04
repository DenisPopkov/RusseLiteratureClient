package ru.popkov.russeliterature.features.home.ui

sealed interface HomeViewAction {
    data class OnCardClick(val cardId: Int) : HomeViewAction
}