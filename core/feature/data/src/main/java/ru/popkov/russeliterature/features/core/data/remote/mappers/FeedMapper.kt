package ru.popkov.russeliterature.features.core.data.remote.mappers

import ru.popkov.russeliterature.features.core.data.local.entities.Article as ArticleEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Poet as PoetEntity
import ru.popkov.russeliterature.features.core.data.remote.dtos.Article as ArticleDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Author as AuthorDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Poet as PoetDto

object FeedMapper {

    fun List<AuthorDto>.toListAuthorEntity(): List<AuthorEntity> =
        map { it.toAuthorEntity() }

    fun List<ArticleDto>.toListArticleEntity(): List<ArticleEntity> =
        map { it.toArticleEntity() }

    fun List<PoetDto>.toListPoetEntity(): List<PoetEntity> =
        map { it.toPoetEntity() }

    private fun PoetDto.toPoetEntity(): PoetEntity =
        PoetEntity(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )

    private fun ArticleDto.toArticleEntity(): ArticleEntity =
        ArticleEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )

    private fun AuthorDto.toAuthorEntity(): AuthorEntity =
        AuthorEntity(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )
}
