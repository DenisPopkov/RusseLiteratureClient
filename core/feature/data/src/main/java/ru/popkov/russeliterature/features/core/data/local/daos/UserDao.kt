package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.popkov.russeliterature.features.core.data.local.AppDatabase
import ru.popkov.russeliterature.features.core.data.local.entities.UserData

@Dao
abstract class UserDao(private val db: AppDatabase) {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addUser(userData: UserData)

    suspend fun deleteAll() = withContext(Dispatchers.IO) { db.clearAllTables() }
}