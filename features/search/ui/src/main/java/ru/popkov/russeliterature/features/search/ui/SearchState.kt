package ru.popkov.russeliterature.features.search.ui

import androidx.compose.runtime.Immutable
import ru.popkov.android.core.feature.components.core.models.SectionFilterItem
import ru.popkov.android.core.feature.components.core.models.SectionType
import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

@Immutable
internal data class SearchState(
    val userId: Long = -1L,
    val filterList: List<SectionFilterItem> = mutableListOf(
        SectionFilterItem(sectionType = SectionType.ALL, isSectionSelected = true),
        SectionFilterItem(sectionType = SectionType.AUTHOR),
        SectionFilterItem(sectionType = SectionType.ARTICLE),
        SectionFilterItem(sectionType = SectionType.POET),
    ),
    val authors: List<Author>? = null,
    val articles: List<Article>? = null,
    val poets: List<Poet>? = null,
    val isEmptyState: Boolean = false,
    val isLoading: Boolean = false,
)
