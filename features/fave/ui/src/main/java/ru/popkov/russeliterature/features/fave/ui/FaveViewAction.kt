package ru.popkov.russeliterature.features.fave.ui

sealed interface FaveViewAction {
    data object OnMainScreenClick : FaveViewAction

    data class OnAuthorFaveClick(val authorId: Long, val isFave: Boolean) : FaveViewAction
    data class OnArticleFaveClick(val articleId: Long, val isFave: Boolean) : FaveViewAction
    data class OnPoetFaveClick(val poetId: Long, val isFave: Boolean) : FaveViewAction
    data class OnSectionClick(val sectionId: Int) : FaveViewAction

    data class OnCardClick(val cardId: Long) : FaveViewAction
}