package ru.popkov.russeliterature.features.core.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ru.popkov.russeliterature.features.core.data.local.entities.Article
import ru.popkov.russeliterature.features.core.data.local.entities.Author
import ru.popkov.russeliterature.features.core.data.local.entities.Poet

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

    @Transaction
    @Query("SELECT * FROM author WHERE id = :authorId")
    abstract suspend fun findAuthorById(authorId: Long): Author

    @Transaction
    @Query("SELECT * FROM article WHERE id = :articleId")
    abstract suspend fun findArticleById(articleId: Long): Article

    @Transaction
    @Query("SELECT * FROM poet WHERE id = :poetId")
    abstract suspend fun findPoetById(poetId: Long): Poet

    @Update(entity = Author::class)
    abstract suspend fun addAuthorToFave(author: Author)

    @Update(entity = Article::class)
    abstract suspend fun addArticleToFave(article: Article)

    @Update(entity = Poet::class)
    abstract suspend fun addPoetToFave(poet: Poet)
}
