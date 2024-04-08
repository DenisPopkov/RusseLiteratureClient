package ru.popkov.russeliterature.features.core

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.popkov.datastore.token.Token
import ru.popkov.russeliterature.features.auth.data.AuthInterceptor
import ru.popkov.russeliterature.features.core.data.remote.api.FeedApi
import ru.popkov.russeliterature.features.core.data.remote.api.QuizApi
import ru.popkov.russeliterature.features.core.data.remote.api.SettingsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAuthInterceptor(token: Token): AuthInterceptor {
        return AuthInterceptor(token)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(createLogging())
            .build()
    }

    private fun createLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.88.112:4041/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun settingsApi(retrofit: Retrofit): SettingsApi =
        retrofit.create(SettingsApi::class.java)

    @Provides
    fun feedApi(retrofit: Retrofit): FeedApi =
        retrofit.create(FeedApi::class.java)

    @Provides
    fun quizApi(retrofit: Retrofit): QuizApi =
        retrofit.create(QuizApi::class.java)
}