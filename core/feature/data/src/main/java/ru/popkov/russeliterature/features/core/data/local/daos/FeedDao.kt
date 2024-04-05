package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.popkov.russeliterature.features.core.data.local.entities.Article
import ru.popkov.russeliterature.features.core.data.local.entities.Author
import ru.popkov.russeliterature.features.core.data.local.entities.Clip
import ru.popkov.russeliterature.features.core.data.local.entities.Feed
import ru.popkov.russeliterature.features.core.data.local.entities.Poet
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFeed(feed: Feed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAuthors(vararg author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(vararg article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPoets(vararg poet: Poet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuiz(quiz: Quiz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addClip(clip: Clip)

    @Query("SELECT * FROM feed")
    suspend fun getFeed(): Feed

    @Query("SELECT * FROM quiz")
    suspend fun getQuiz(): Quiz

    @Query("SELECT * FROM clip")
    suspend fun getClip(): Clip

    @Query("SELECT * FROM author")
    suspend fun getAuthors(): List<Author>

    @Query("SELECT * FROM article")
    suspend fun getArticles(): List<Article>

    @Query("SELECT * FROM poet")
    suspend fun getPoets(): List<Poet>

    @Transaction
    @Query("SELECT * FROM author WHERE id = :id")
    suspend fun findAuthorById(id: Int): Author

    @Transaction
    @Query("SELECT * FROM article WHERE id = :id")
    suspend fun findArticleById(id: Int): Article

    @Transaction
    @Query("SELECT * FROM poet WHERE id = :id")
    suspend fun findPoetById(id: Int): Poet

    @Transaction
    @Query("SELECT * FROM author WHERE isFave = true")
    suspend fun findFaveAuthors(): List<Author>

    @Transaction
    @Query("SELECT * FROM article WHERE isFave = true")
    suspend fun findFaveArticles(): List<Article>

    @Transaction
    @Query("SELECT * FROM poet WHERE isFave = true")
    suspend fun findFavePoets(): List<Poet>
}
