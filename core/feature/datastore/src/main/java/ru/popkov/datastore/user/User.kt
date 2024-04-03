package ru.popkov.datastore.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.take
import ru.popkov.android.core.feature.datastore.ProtoUser
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class User @Inject constructor(
    private val id: DataStore<ProtoUser>,
) {

    val userId get() = id.data.take(1)

    suspend fun saveUserId(userId: Long) = id.updateData {
        Timber.d("Saving id: $id")
        it.toBuilder().setId(userId).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class TokenModule {

    @Provides
    @Singleton
    fun provideUserId(@ApplicationContext context: Context) = User(context.dataStore)

    private val Context.dataStore: DataStore<ProtoUser> by dataStore(
        fileName = "user.proto",
        serializer = UserSerializer
    )
}