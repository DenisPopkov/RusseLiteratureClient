package ru.popkov.russeliterature.features.auth.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.popkov.russeliterature.features.auth.data.local.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun add(vararg user: User)

    @Transaction
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun findUser(id: Long): Long
}