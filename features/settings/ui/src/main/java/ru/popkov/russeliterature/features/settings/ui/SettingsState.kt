package ru.popkov.russeliterature.features.settings.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsState(
    val userName: String = "Фёдор Достоевский",
    val userImage: String = "",
    val userId: Long = -1L,
    val isLoading: Boolean = false,
)
