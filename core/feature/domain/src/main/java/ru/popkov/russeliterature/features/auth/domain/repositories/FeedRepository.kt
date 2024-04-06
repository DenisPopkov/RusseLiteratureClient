package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

interface FeedRepository {

    suspend fun addAuthorToFave(
        userId: Long,
        authorId: Long,
    ): List<Author>

    suspend fun addArticleToFave(
        userId: Long,
        articleId: Long,
    ): List<Article>

    suspend fun addPoetToFave(
        userId: Long,
        poetId: Long,
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

    suspend fun getAuthorsFromLocal(): List<Author>

    suspend fun getArticlesFromLocal(): List<Article>

    suspend fun getPoetsFromLocal(): List<Poet>

    suspend fun getFaveAuthors(): List<Author>
    suspend fun getFaveArticles(): List<Article>
    suspend fun getFavePoets(): List<Poet>
}