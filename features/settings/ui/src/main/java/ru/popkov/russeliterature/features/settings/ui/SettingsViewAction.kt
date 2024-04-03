package ru.popkov.russeliterature.features.settings.ui

sealed interface SettingsViewAction {
    data class OnDeleteAccountClick(val userId: Long): SettingsViewAction
}