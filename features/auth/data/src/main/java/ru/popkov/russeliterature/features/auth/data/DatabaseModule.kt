package ru.popkov.russeliterature.features.auth.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.popkov.russeliterature.features.auth.data.local.AppDatabase
import ru.popkov.russeliterature.features.auth.data.local.daos.UserDao
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
                AppDatabase::class.java, "database-name"
            )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun userDao(database: AppDatabase): UserDao =
        database.userDao()

}