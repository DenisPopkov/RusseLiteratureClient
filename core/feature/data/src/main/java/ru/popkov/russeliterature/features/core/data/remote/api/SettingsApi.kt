package ru.popkov.russeliterature.features.core.data.remote.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query
import ru.popkov.russeliterature.features.core.data.remote.dtos.Settings

interface SettingsApi {
    @GET("user")
    suspend fun getSettings(
        @Query("userId") userId: Long,
    ): Settings
    @DELETE("user")
    suspend fun deleteUserAccount(
        @Query("userId") userId: Long,
    )
}
