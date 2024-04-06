package ru.popkov.russeliterature.features.core.data.repositories

import android.util.Log
import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Poet
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListArticlesDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListAuthorsDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListPoetsDomain
import ru.popkov.russeliterature.features.core.data.remote.api.FeedApi
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toListArticleEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toListAuthorEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toListPoetEntity
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class FeedRepository @Inject constructor(
    private val feedApi: FeedApi,
    private val feedDao: FeedDao,
) : FeedRepository {

    override suspend fun addAuthorToFave(
        userId: Long,
        authorId: Long,
    ) {
        feedApi.addAuthorToFave(userId, authorId)
    }

    override suspend fun addArticleToFave(
        userId: Long,
        articleId: Long,
    ) {
        feedApi.addArticleToFave(userId, articleId)
    }

    override suspend fun addPoetToFave(
        userId: Long,
        poetId: Long,
    ) {
        feedApi.addPoetToFave(userId, poetId)
    }

    override suspend fun getAuthors(userId: Long): List<Author> {
        val authors = feedApi.getAuthors(userId)
        Log.d("efefe", "response - $authors")
        feedDao.addAuthors(*authors.toListAuthorEntity().toTypedArray())
        return feedDao.getAuthors().toListAuthorsDomain()
    }

    override suspend fun getArticles(userId: Long): List<Article> {
        val articles = feedApi.getArticles(userId)
        feedDao.addArticles(*articles.toListArticleEntity().toTypedArray())
        return feedDao.getArticles().toListArticlesDomain()
    }

    override suspend fun getPoets(userId: Long): List<Poet> {
        val poets = feedApi.getPoets(userId)
        feedDao.addPoets(*poets.toListPoetEntity().toTypedArray())
        return feedDao.getPoets().toListPoetsDomain()
    }
}