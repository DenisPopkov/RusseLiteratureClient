package ru.popkov.russeliterature.features.core.data.remote.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import ru.popkov.russeliterature.features.core.data.remote.dtos.Settings

interface SettingsApi {
    @GET("user")
    suspend fun getSettings(
        
    ): Settings
    @DELETE("user")
    suspend fun deleteUserAccount()
}
