package ru.popkov.russeliterature.features.core

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.popkov.russeliterature.features.core.data.local.AppDatabase
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase =
        Room
            .databaseBuilder(
                context,
                AppDatabase::class.java, "russe-literature-database"
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun feedDao(database: AppDatabase): FeedDao =
        database.feedDao()
}
