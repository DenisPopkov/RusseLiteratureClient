package ru.popkov.russeliterature.features.auth.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.popkov.russeliterature.features.auth.data.remote.api.UserApi

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun userApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)
}