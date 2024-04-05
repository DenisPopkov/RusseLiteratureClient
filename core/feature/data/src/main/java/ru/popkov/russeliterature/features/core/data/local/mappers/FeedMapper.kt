package ru.popkov.russeliterature.features.core.data.local.mappers

import ru.popkov.russeliterature.features.auth.domain.model.Answer as AnswerDomain
import ru.popkov.russeliterature.features.auth.domain.model.Article as ArticlesDomain
import ru.popkov.russeliterature.features.auth.domain.model.Author as AuthorsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Clip as ClipDomain
import ru.popkov.russeliterature.features.auth.domain.model.ClipText as ClipTextDomain
import ru.popkov.russeliterature.features.auth.domain.model.Feed as FeedDomain
import ru.popkov.russeliterature.features.auth.domain.model.Poet as PoetsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Quiz as QuizDomain
import ru.popkov.russeliterature.features.core.data.local.entities.Answer as AnswerEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Article as ArticlesEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Author as AuthorsEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Clip as ClipEntity
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText as ClipTextEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Feed as FeedEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Poet as PoetsEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz as QuizEntity

object FeedMapper {

    fun FeedEntity.toFeedDomain(): FeedDomain =
        FeedDomain(
            authors = this.authors.toListAuthorsDomain(),
            articles = this.articles.toListArticlesDomain(),
            poets = this.poets.toListPoetsDomain(),
        )

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

    fun ClipEntity.toClipDomain() =
        ClipDomain(
            id = this.id,
            text = this.text.toListClipTextDomain(),
            quiz = this.quiz,
        )

    private fun ClipTextEntity.toClipTextDomain() =
        ClipTextDomain(
            title = this.title,
            text = this.text,
        )

    private fun List<ClipTextEntity>.toListClipTextDomain(): List<ClipTextDomain> =
        map { it.toClipTextDomain() }

    private fun QuizEntity.toQuizDomain() =
        QuizDomain(
            id = this.id,
            question = this.question,
            description = this.description,
            image = this.image,
            answers = this.answers.toAnswerDomain(),
        )

    private fun AnswerEntity.toAnswerDomain() =
        AnswerDomain(
            id = this.id,
            text = this.text,
            isRight = this.isRight,
        )

    private fun List<AnswerEntity>.toAnswerDomain(): List<AnswerDomain> =
        map { it.toAnswerDomain() }
}
