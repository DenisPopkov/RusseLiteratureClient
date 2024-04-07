package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.popkov.russeliterature.features.core.data.local.entities.Answer
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText
import ru.popkov.russeliterature.features.core.data.local.entities.Clips
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz

@Dao
abstract class ClipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAnswers(vararg answer: Answer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addClipTexts(vararg clipText: ClipText)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addQuiz(quiz: Quiz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addClip(clip: Clips)

    @Query("SELECT * FROM quiz WHERE quizId = :quizId")
    abstract suspend fun getQuiz(quizId: Long): Quiz

    @Query("SELECT * FROM clips WHERE clipId = :clipId")
    abstract suspend fun getClip(clipId: Long): Clips
}
