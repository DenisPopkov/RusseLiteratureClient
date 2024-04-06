package ru.popkov.russeliterature.features.home.ui

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.auth.domain.model.Feed

@Immutable
internal data class HomeState(
    val userId: Long = -1L,
    val feed: Feed? = null,
    val isLoading: Boolean = false,
)
