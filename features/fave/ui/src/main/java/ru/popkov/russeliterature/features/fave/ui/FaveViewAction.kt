package ru.popkov.russeliterature.features.fave.ui

sealed interface FaveViewAction {
    data object OnMainScreenClick : FaveViewAction

    data class OnAuthorFaveClick(val userId: Long, val authorId: Long, val isFave: Boolean) : FaveViewAction
    data class OnArticleFaveClick(val userId: Long, val articleId: Long, val isFave: Boolean) : FaveViewAction
    data class OnPoetFaveClick(val userId: Long, val poetId: Long, val isFave: Boolean) : FaveViewAction
}