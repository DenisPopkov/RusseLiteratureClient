package ru.popkov.russeliterature.features.section.ui

sealed interface SectionViewAction {
    data object OnBackClick : SectionViewAction
    data class OnSectionClick(val cardId: Long) : SectionViewAction

    data class OnAuthorFaveClick(val authorId: Long, val isFave: Boolean) :
        SectionViewAction

    data class OnArticleFaveClick(val articleId: Long, val isFave: Boolean) :
        SectionViewAction

    data class OnPoetFaveClick(val poetId: Long, val isFave: Boolean) :
        SectionViewAction

    data class OnCardClick(val cardId: Long) : SectionViewAction
}