package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Settings

interface SettingsRepository {
    suspend fun getSettings(): Settings
    suspend fun deleteUserAccount()
}