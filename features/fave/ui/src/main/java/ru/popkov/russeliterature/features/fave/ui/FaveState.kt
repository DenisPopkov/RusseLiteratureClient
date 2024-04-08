package ru.popkov.russeliterature.features.fave.ui

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

@Immutable
internal data class FaveState(
    val authors: List<Author>? = null,
    val articles: List<Article>? = null,
    val poets: List<Poet>? = null,
    val isEmptyState: Boolean = false,
    val isLoading: Boolean = false,
)
