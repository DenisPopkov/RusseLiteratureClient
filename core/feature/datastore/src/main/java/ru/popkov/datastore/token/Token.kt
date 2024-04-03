package ru.popkov.datastore.token

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.take
import ru.popkov.android.core.feature.datastore.ProtoToken
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class Token @Inject constructor(private val token: DataStore<ProtoToken>) {

    val jwt get() = token.data.take(1)

    suspend fun saveToken(jwt: String) = token.updateData {
        Timber.d("Saving token: $token")
        it.toBuilder().setToken(jwt).build()
    }

    suspend fun deleteToken() = token.updateData {
        Timber.d("Deleting token: $token")
        it.toBuilder().clearToken().build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class TokenModule {

    @Provides
    @Singleton
    fun provideToken(@ApplicationContext context: Context) = Token(context.dataStore)

    private val Context.dataStore: DataStore<ProtoToken> by dataStore(
        fileName = "jwt.proto",
        serializer = TokenSerializer
    )
}