package ru.popkov.russeliterature.features.auth.data.repositories

import ru.popkov.russeliterature.features.auth.data.remote.api.FeedApi
import ru.popkov.russeliterature.features.auth.data.remote.mappers.FeedMapper.toArticlesDomain
import ru.popkov.russeliterature.features.auth.data.remote.mappers.FeedMapper.toAuthorsDomain
import ru.popkov.russeliterature.features.auth.data.remote.mappers.FeedMapper.toFeedDomain
import ru.popkov.russeliterature.features.auth.data.remote.mappers.FeedMapper.toPoetsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Article
import ru.popkov.russeliterature.features.auth.domain.model.Author
import ru.popkov.russeliterature.features.auth.domain.model.Feed
import ru.popkov.russeliterature.features.auth.domain.model.Poet
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class FeedRepository @Inject constructor(
    private val feedApi: FeedApi,
) : FeedRepository {

    override suspend fun getFeed(userId: Long): Feed {
        return feedApi.getFeed(userId).toFeedDomain()
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
        return feedApi.getAuthors(userId).toAuthorsDomain()
    }

    override suspend fun getArticles(userId: Long): List<Article> {
        return feedApi.getArticles(userId).toArticlesDomain()
    }

    override suspend fun getPoets(userId: Long): List<Poet> {
        return feedApi.getPoets(userId).toPoetsDomain()
    }
}