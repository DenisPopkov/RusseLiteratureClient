package ru.popkov.russeliterature.features.home.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

//    @Singleton
//    @Provides
//    fun database(@ApplicationContext context: Context): AppDatabase =
//        Room
//            .databaseBuilder(
//                context,
//                AppDatabase::class.java, "database-name"
//            )
//            .fallbackToDestructiveMigration()
//            .build()
}