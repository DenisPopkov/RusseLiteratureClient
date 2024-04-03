package ru.popkov.russeliterature.features.settings.ui

internal sealed interface SettingsViewEffect {

    data class ShowError(val errorMessage: String) : SettingsViewEffect
}