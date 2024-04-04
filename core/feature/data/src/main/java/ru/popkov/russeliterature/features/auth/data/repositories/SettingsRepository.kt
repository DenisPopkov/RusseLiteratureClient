package ru.popkov.russeliterature.features.auth.data.repositories

import ru.popkov.datastore.token.Token
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.features.auth.data.remote.api.SettingsApi
import ru.popkov.russeliterature.features.auth.data.remote.mappers.SettingsMapper.toEntity
import ru.popkov.russeliterature.features.auth.domain.model.Settings
import ru.popkov.russeliterature.features.auth.domain.repositories.SettingsRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class SettingsRepository @Inject constructor(
    private val settingsApi: SettingsApi,
    private val userDataStore: User,
    private val tokenDataStore: Token,
) : SettingsRepository {
    override suspend fun getSettings(userId: Long): Settings {
        return settingsApi.getSettings(userId).toEntity()
    }

    override suspend fun deleteUserAccount(userId: Long) {
        settingsApi.deleteUserAccount(userId)
        userDataStore.deleteUserId()
        tokenDataStore.deleteToken()
    }

}