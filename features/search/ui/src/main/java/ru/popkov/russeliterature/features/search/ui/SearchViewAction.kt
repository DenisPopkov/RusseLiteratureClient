package ru.popkov.russeliterature.features.search.ui

import ru.popkov.android.core.feature.components.core.models.SectionType

sealed interface SearchViewAction {
    data object OnMainScreenClick : SearchViewAction
    data class OnAuthorFaveClick(val authorId: Long, val isFave: Boolean) :
        SearchViewAction

    data class OnArticleFaveClick(val articleId: Long, val isFave: Boolean) :
        SearchViewAction

    data class OnPoetFaveClick(val poetId: Long, val isFave: Boolean) :
        SearchViewAction

    data class OnSectionItemClick(val sectionType: SectionType) : SearchViewAction
    data class OnSearchChange(val searchText: String) : SearchViewAction
    data class OnSectionClick(val sectionId: Int) : SearchViewAction
}