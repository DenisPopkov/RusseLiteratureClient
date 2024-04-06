package ru.popkov.russeliterature.features.section.ui

sealed interface SectionViewAction {
    data object OnBackClick : SectionViewAction
    data class OnSectionClick(val cardId: Long) : SectionViewAction

    data class OnAuthorFaveClick(val userId: Long, val authorId: Long, val isFave: Boolean) :
        SectionViewAction

    data class OnArticleFaveClick(val userId: Long, val articleId: Long, val isFave: Boolean) :
        SectionViewAction

    data class OnPoetFaveClick(val userId: Long, val poetId: Long, val isFave: Boolean) :
        SectionViewAction
}