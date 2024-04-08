package ru.popkov.russeliterature.features.home.ui

sealed interface HomeViewAction {
    data class OnCardClick(val cardId: Long) : HomeViewAction
    data class OnSectionClick(val sectionId: Int) : HomeViewAction
    data class OnAuthorFaveClick(val authorId: Long, val isFave: Boolean) : HomeViewAction
    data class OnArticleFaveClick(val articleId: Long, val isFave: Boolean) : HomeViewAction
    data class OnPoetFaveClick(val poetId: Long, val isFave: Boolean) : HomeViewAction
}