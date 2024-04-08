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
        @Query("authorId") authorId: Long,
    )

    @PATCH("/articles")
    suspend fun addArticleToFave(
        @Query("articleId") articleId: Long,
    )

    @PATCH("/poets")
    suspend fun addPoetToFave(
        
        @Query("poetId") poetId: Long,
    )

    @GET("/authors")
    suspend fun getAuthors(
        
    ): List<Author>

    @GET("/articles")
    suspend fun getArticles(
        
    ): List<Article>

    @GET("/poets")
    suspend fun getPoets(
        
    ): List<Poet>
}
