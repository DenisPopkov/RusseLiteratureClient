package ru.popkov.russeliterature.features.core.data.remote.api

import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query
import ru.popkov.russeliterature.features.core.data.remote.dtos.Article
import ru.popkov.russeliterature.features.core.data.remote.dtos.Author
import ru.popkov.russeliterature.features.core.data.remote.dtos.Feed
import ru.popkov.russeliterature.features.core.data.remote.dtos.Poet

interface FeedApi {

    @GET("/feed")
    suspend fun getFeed(
        @Query("userId") userId: Long,
    ): Feed

    @PATCH("/authors")
    suspend fun addAuthorToFave(
        @Query("userId") userId: Long,
        @Query("authorId") authorId: Long,
        @Query("isFave") isFave: String,
    )

    @PATCH("/articles")
    suspend fun addArticleToFave(
        @Query("userId") userId: Long,
        @Query("articleId") articleId: Long,
        @Query("isFave") isFave: String,
    )

    @PATCH("/poets")
    suspend fun addPoetToFave(
        @Query("userId") userId: Long,
        @Query("poetId") poetId: Long,
        @Query("isFave") isFave: String,
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
