package ru.popkov.russeliterature.features.settings.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class SettingsState(
    val userName: String = "",
    val userImage: String = "",
    val isLoading: Boolean = false,
)
