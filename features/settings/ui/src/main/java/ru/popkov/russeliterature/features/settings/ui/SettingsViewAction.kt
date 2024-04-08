package ru.popkov.russeliterature.features.settings.ui

sealed interface SettingsViewAction {
    data object OnDeleteAccountClick : SettingsViewAction
    data object OnExitAccountClick: SettingsViewAction
}