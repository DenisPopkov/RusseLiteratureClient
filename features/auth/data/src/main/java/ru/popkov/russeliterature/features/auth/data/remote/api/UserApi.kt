package ru.popkov.russeliterature.features.auth.data.remote.api

import retrofit2.http.POST

interface UserApi {

    // TODO() actualize method return call and payload when backend will be ready
    @POST("auth")
    suspend fun loginUser(): Boolean

}