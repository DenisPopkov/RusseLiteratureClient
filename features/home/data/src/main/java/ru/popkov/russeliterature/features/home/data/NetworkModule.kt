package ru.popkov.russeliterature.features.home.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://russeliterature/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}