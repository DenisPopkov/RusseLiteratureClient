package ru.popkov.russeliterature.features.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.popkov.russeliterature.features.auth.data.local.daos.UserDao
import ru.popkov.russeliterature.features.auth.data.local.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
