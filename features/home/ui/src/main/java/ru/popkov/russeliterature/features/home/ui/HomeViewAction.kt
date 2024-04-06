package ru.popkov.russeliterature.features.home.ui

sealed interface HomeViewAction {
    data class OnCardClick(val cardId: Int) : HomeViewAction
    data class OnFaveClick(val userId: Long, val cardId: Long, val isFave: Boolean) : HomeViewAction
}