package ru.popkov.russeliterature.features.core.data.local.mappers

import ru.popkov.russeliterature.features.auth.domain.model.Answer as AnswerDomain
import ru.popkov.russeliterature.features.auth.domain.model.Clip as ClipDomain
import ru.popkov.russeliterature.features.auth.domain.model.ClipText as ClipTextDomain
import ru.popkov.russeliterature.features.auth.domain.model.Quiz as QuizDomain
import ru.popkov.russeliterature.features.core.data.local.entities.Answer as AnswerEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Clip as ClipEntity
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText as ClipTextEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz as QuizEntity

object QuizMapper {

    fun QuizEntity.toQuizDomain(): QuizDomain =
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

    fun ClipEntity.toClipDomain(): ClipDomain =
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
}