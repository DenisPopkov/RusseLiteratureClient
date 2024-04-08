package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet

interface FeedRepository {

    suspend fun addAuthorToFave(

        authorId: Long,
    ): List<Author>

    suspend fun addArticleToFave(
        articleId: Long,
    ): List<Article>

    suspend fun addPoetToFave(
        poetId: Long,
    ): List<Poet>

    suspend fun getAuthors(): List<Author>

    suspend fun getArticles(): List<Article>

    suspend fun getPoets(): List<Poet>

    suspend fun getAuthorsFromLocal(): List<Author>

    suspend fun getArticlesFromLocal(): List<Article>

    suspend fun getPoetsFromLocal(): List<Poet>

    suspend fun getFaveAuthors(): List<Author>
    suspend fun getFaveArticles(): List<Article>
    suspend fun getFavePoets(): List<Poet>
}