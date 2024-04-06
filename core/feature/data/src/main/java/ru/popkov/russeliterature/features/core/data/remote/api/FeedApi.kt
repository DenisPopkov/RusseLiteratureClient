package ru.popkov.russeliterature.features.core.data.remote.api

import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.popkov.russeliterature.features.core.data.remote.dtos.Article
import ru.popkov.russeliterature.features.core.data.remote.dtos.Author
import ru.popkov.russeliterature.features.core.data.remote.dtos.Poet

interface FeedApi {

    @PATCH("/authors")
    suspend fun addAuthorToFave(
        @Query("userId") userId: Long,
        @Query("authorId") authorId: Long,
    )

    @PATCH("/articles")
    suspend fun addArticleToFave(
        @Query("userId") userId: Long,
        @Query("articleId") articleId: Long,
    )

    @PATCH("/poets")
    suspend fun addPoetToFave(
        @Query("userId") userId: Long,
        @Query("poetId") poetId: Long,
    )

    @GET("/authors")
    suspend fun getAuthors(
        @Query("userId") userId: Long,
    ): List<Author>

    @GET("/articles")
    suspend fun getArticles(
        @Query("userId") userId: Long,
    ): List<Article>

    @GET("/poets")
    suspend fun getPoets(
        @Query("userId") userId: Long,
    ): List<Poet>
}
