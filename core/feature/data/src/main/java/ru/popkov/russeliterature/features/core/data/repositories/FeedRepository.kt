package ru.popkov.russeliterature.features.core.data.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Feed
import ru.popkov.russeliterature.features.auth.domain.model.Poet
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toFeedDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListArticlesDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListAuthorsDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.FeedMapper.toListPoetsDomain
import ru.popkov.russeliterature.features.core.data.remote.api.FeedApi
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toFeedEntity
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

    override suspend fun getFeed(userId: Long): Feed {
        val feed = feedApi.getFeed(userId)
        feedDao.addFeed(feed.toFeedEntity())
        return feedDao.getFeed().toFeedDomain()
    }

    override suspend fun addAuthorToFave(
        userId: Long,
        authorId: Long,
        isFave: String,
    ) {
        feedApi.addAuthorToFave(userId, authorId, isFave)
    }

    override suspend fun addArticleToFave(
        userId: Long,
        articleId: Long,
        isFave: String,
    ) {
        feedApi.addArticleToFave(userId, articleId, isFave)
    }

    override suspend fun addPoetToFave(
        userId: Long,
        poetId: Long,
        isFave: String,
    ) {
        feedApi.addPoetToFave(userId, poetId, isFave)
    }

    override suspend fun getAuthors(userId: Long): List<Author> {
        val authors = feedApi.getAuthors(userId)
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