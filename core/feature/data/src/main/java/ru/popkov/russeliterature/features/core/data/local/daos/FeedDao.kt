package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.popkov.russeliterature.features.core.data.local.entities.Article
import ru.popkov.russeliterature.features.core.data.local.entities.Author
import ru.popkov.russeliterature.features.core.data.local.entities.Clip
import ru.popkov.russeliterature.features.core.data.local.entities.Poet
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz

@Dao
abstract class FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addAuthors(vararg author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addArticles(vararg article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addPoets(vararg poet: Poet)

    @Query("SELECT * FROM author")
    abstract suspend fun getAuthors(): List<Author>

    @Query("SELECT * FROM article")
    abstract suspend fun getArticles(): List<Article>

    @Query("SELECT * FROM poet")
    abstract suspend fun getPoets(): List<Poet>

    @Transaction
    @Query("SELECT * FROM author WHERE isFave = true")
    abstract suspend fun findFaveAuthors(): List<Author>

    @Transaction
    @Query("SELECT * FROM article WHERE isFave = true")
    abstract suspend fun findFaveArticles(): List<Article>

    @Transaction
    @Query("SELECT * FROM poet WHERE isFave = true")
    abstract suspend fun findFavePoets(): List<Poet>
}
