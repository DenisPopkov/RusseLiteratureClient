package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.popkov.russeliterature.features.core.data.local.entities.Clip
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz

@Dao
abstract class ClipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addQuiz(quiz: Quiz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addClip(clip: Clip)

    @Query("SELECT * FROM quiz WHERE id = :quizId")
    abstract suspend fun getQuiz(quizId: Long): Quiz

    @Query("SELECT * FROM clip WHERE id = :clipId")
    abstract suspend fun getClip(clipId: Long): Clip
}
