package ru.popkov.datastore.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.popkov.android.core.feature.datastore.ProtoSettings
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class Settings @Inject constructor(private val isLight: DataStore<ProtoSettings>) {

    val isLightMode get() = isLight.data

    suspend fun changeMode() = isLight.updateData {
        Timber.d("Saving isLightMode: was ${it.isLight}, now ${!it.isLight}")
        it.toBuilder().setIsLight(!it.isLight).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class SettingsModule {

    @Provides
    @Singleton
    fun provideSettings(@ApplicationContext context: Context) = Settings(context.dataStore)

    private val Context.dataStore: DataStore<ProtoSettings> by dataStore(
        fileName = "settings.proto",
        serializer = SettingsSerializer
    )
}