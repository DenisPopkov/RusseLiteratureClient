package ru.popkov.russeliterature.features.library.ui.artists

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.library.ui.ItemViewState

@Immutable
internal data class ArtistsViewState(
    val items: List<ItemViewState<ArtistsViewAction>> = emptyList(),
)