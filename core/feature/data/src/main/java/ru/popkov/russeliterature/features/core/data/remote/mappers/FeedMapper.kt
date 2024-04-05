package ru.popkov.russeliterature.features.core.data.remote.mappers

import ru.popkov.russeliterature.features.core.data.local.entities.Article as ArticleEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Feed as FeedEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Poet as PoetEntity
import ru.popkov.russeliterature.features.core.data.remote.dtos.Article as ArticleDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Author as AuthorDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Feed as FeedDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Poet as PoetDto

object FeedMapper {

    fun FeedDto.toFeedEntity(): FeedEntity =
        FeedEntity(
            id = 0L,
            authors = this.authors.toListAuthorEntity(),
            articles = this.articles.toListArticleEntity(),
            poets = this.poets.toListPoetEntity(),
        )

    fun List<AuthorDto>.toListAuthorEntity(): List<AuthorEntity> =
        map { it.toAuthorEntity() }

    fun List<ArticleDto>.toListArticleEntity(): List<ArticleEntity> =
        map { it.toArticleEntity() }

    fun List<PoetDto>.toListPoetEntity(): List<PoetEntity> =
        map { it.toPoetEntity() }

    private fun PoetDto.toPoetEntity(): PoetEntity =
        PoetEntity(
            id = 0L,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun ArticleDto.toArticleEntity(): ArticleEntity =
        ArticleEntity(
            id = 0L,
            name = this.name,
            description = this.description,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun AuthorDto.toAuthorEntity(): AuthorEntity =
        AuthorEntity(
            id = 0L,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun String.convertStringBoolean(): Boolean = this == "true"
}
