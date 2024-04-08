package ru.popkov.russeliterature.features.core.data.local.mappers

import ru.popkov.russeliterature.features.auth.domain.model.Article as ArticlesDomain
import ru.popkov.russeliterature.features.auth.domain.model.Author as AuthorsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Poet as PoetsDomain
import ru.popkov.russeliterature.features.core.data.local.entities.Article as ArticlesEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorsEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Poet as PoetsEntity

object FeedMapper {

    private fun AuthorEntity.toAuthorsDomain(): AuthorsDomain =
        AuthorsDomain(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )

    private fun ArticlesEntity.toArticlesDomain(): ArticlesDomain =
        ArticlesDomain(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )

    private fun PoetsEntity.toPoetsDomain(): PoetsDomain =
        PoetsDomain(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave,
        )

    fun List<AuthorsEntity>.toListAuthorsDomain(): List<AuthorsDomain> =
        map { it.toAuthorsDomain() }

    fun List<ArticlesEntity>.toListArticlesDomain(): List<ArticlesDomain> =
        map { it.toArticlesDomain() }

    fun List<PoetsEntity>.toListPoetsDomain(): List<PoetsDomain> =
        map { it.toPoetsDomain() }
}
