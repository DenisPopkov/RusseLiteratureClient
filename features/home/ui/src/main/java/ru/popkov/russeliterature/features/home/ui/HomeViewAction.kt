package ru.popkov.russeliterature.features.home.ui

sealed interface HomeViewAction {
    data class OnCardClick(val cardId: Int) : HomeViewAction
    data class OnAuthorFaveClick(val userId: Long, val authorId: Long, val isFave: Boolean) : HomeViewAction
    data class OnArticleFaveClick(val userId: Long, val articleId: Long, val isFave: Boolean) : HomeViewAction
    data class OnPoetFaveClick(val userId: Long, val poetId: Long, val isFave: Boolean) : HomeViewAction
}