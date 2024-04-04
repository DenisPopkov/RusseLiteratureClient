package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Feed
import ru.popkov.russeliterature.features.auth.domain.model.Poet

interface FeedRepository {
    suspend fun getFeed(
        userId: Long,
    ): Feed

    suspend fun addAuthorToFave(
        userId: Long,
        authorId: Long,
        isFave: String,
    ): List<Author>

    suspend fun addArticleToFave(
        userId: Long,
        articleId: Long,
        isFave: String,
    ): List<Article>

    suspend fun addPoetToFave(
        userId: Long,
        poetId: Long,
        isFave: String,
    ): List<Poet>

    suspend fun getAuthors(
        userId: Long,
    ): List<Author>

    suspend fun getArticles(
        userId: Long,
    ): List<Article>

    suspend fun getPoets(
        userId: Long,
    ): List<Poet>
}