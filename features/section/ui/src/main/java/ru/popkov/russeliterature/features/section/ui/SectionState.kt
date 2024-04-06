package ru.popkov.russeliterature.features.section.ui

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

@Immutable
internal data class SectionState(
    val userId: Long = -1L,
    val authors: List<Author>? = null,
    val articles: List<Article>? = null,
    val poets: List<Poet>? = null,
    val isLoading: Boolean = false,
)
