package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

interface FeedRepository {

    suspend fun addAuthorToFave(
        userId: Long,
        authorId: Long,
    )

    suspend fun addArticleToFave(
        userId: Long,
        articleId: Long,
    )

    suspend fun addPoetToFave(
        userId: Long,
        poetId: Long,
    )

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