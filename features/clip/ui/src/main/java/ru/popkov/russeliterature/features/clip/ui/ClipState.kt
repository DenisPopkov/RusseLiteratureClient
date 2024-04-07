package ru.popkov.russeliterature.features.clip.ui

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.auth.domain.model.Clip
import ru.popkov.russeliterature.features.auth.domain.model.Quiz

@Immutable
internal data class ClipState(
    val userId: Long = -1L,
    val clip: Clip = Clip(0L, emptyList(), Quiz(0L, "", "", "", emptyList()), ""),
    val isLoading: Boolean = false,
)
