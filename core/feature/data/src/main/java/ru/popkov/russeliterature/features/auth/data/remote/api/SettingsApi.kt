package ru.popkov.russeliterature.features.auth.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.popkov.russeliterature.features.auth.data.remote.dtos.Settings

interface SettingsApi {
    @GET("user")
    suspend fun getSettings(
        @Query("userId") userId: Long,
    ): Settings
}