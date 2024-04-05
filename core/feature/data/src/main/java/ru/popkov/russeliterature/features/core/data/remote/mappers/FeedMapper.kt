package ru.popkov.russeliterature.features.core.data.remote.mappers

import ru.popkov.russeliterature.features.core.data.remote.dtos.Answer as AnswerDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Article as ArticlesDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Author as AuthorsDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Clip as ClipDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.ClipText as ClipTextDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Feed as FeedDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Poet as PoetsDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Quiz as QuizDto
import ru.popkov.russeliterature.features.auth.domain.model.Answer as AnswerDomain
import ru.popkov.russeliterature.features.auth.domain.model.Article as ArticlesDomain
import ru.popkov.russeliterature.features.auth.domain.model.Author as AuthorsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Clip as ClipDomain
import ru.popkov.russeliterature.features.auth.domain.model.ClipText as ClipTextDomain
import ru.popkov.russeliterature.features.auth.domain.model.Feed as FeedDomain
import ru.popkov.russeliterature.features.auth.domain.model.Poet as PoetsDomain
import ru.popkov.russeliterature.features.auth.domain.model.Quiz as QuizDomain

object FeedMapper {

    fun FeedDto.toFeedDomain() =
        FeedDomain(
            authors = this.authors.toAuthorsDomain(),
            articles = this.articles.toArticlesDomain(),
            poets = this.poets.toPoetsDomain(),
        )

    private fun AuthorsDto.toAuthorsDomain() =
        AuthorsDomain(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun ArticlesDto.toArticlesDomain() =
        ArticlesDomain(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun PoetsDto.toPoetsDomain() =
        PoetsDomain(
            id = this.id,
            name = this.name,
            image = this.image,
            clip = this.clip,
            isFave = this.isFave.convertStringBoolean(),
        )

    private fun String.convertStringBoolean(): Boolean = this == "true"

    fun List<AuthorsDto>.toAuthorsDomain(): List<AuthorsDomain> =
        map { it.toAuthorsDomain() }

    fun List<ArticlesDto>.toArticlesDomain(): List<ArticlesDomain> =
        map { it.toArticlesDomain() }

    fun List<PoetsDto>.toPoetsDomain(): List<PoetsDomain> =
        map { it.toPoetsDomain() }


    fun ClipDto.toClipDomain() =
        ClipDomain(
            id = this.id,
            text = this.text.toClipTextDomain(),
            quiz = this.quiz.toQuizDomain(),
        )

    private fun ClipTextDto.toClipTextDomain() =
        ClipTextDomain(
            title = this.title,
            text = this.text,
        )

    private fun List<ClipTextDto>.toClipTextDomain(): List<ClipTextDomain> =
        map { it.toClipTextDomain() }

    fun QuizDto.toQuizDomain() =
        QuizDomain(
            id = this.id,
            question = this.question,
            description = this.description,
            image = this.image,
            answers = this.answers.toAnswerDomain(),
        )

    private fun AnswerDto.toAnswerDomain() =
        AnswerDomain(
            id = this.id,
            text = this.text,
            isRight = this.isRight,
        )

    private fun List<AnswerDto>.toAnswerDomain(): List<AnswerDomain> =
        map { it.toAnswerDomain() }
}
