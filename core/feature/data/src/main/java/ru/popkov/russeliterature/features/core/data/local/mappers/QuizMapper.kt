package ru.popkov.russeliterature.features.core.data.local.mappers

import ru.popkov.russeliterature.features.auth.domain.model.Answer as AnswerDomain
import ru.popkov.russeliterature.features.auth.domain.model.Clip as ClipDomain
import ru.popkov.russeliterature.features.auth.domain.model.ClipText as ClipTextDomain
import ru.popkov.russeliterature.features.auth.domain.model.Quiz as QuizDomain
import ru.popkov.russeliterature.features.core.data.local.entities.Answer as AnswerEntity
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText as ClipTextEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Clips as ClipEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz as QuizEntity

object QuizMapper {

    fun QuizEntity.toQuizDomain(): QuizDomain =
        QuizDomain(
            quizId = this.quizId,
            question = this.question,
            description = this.description,
            image = this.image,
            answers = this.answers.answerList.map { it.toAnswerDomain() },
        )

    private fun AnswerEntity.toAnswerDomain() =
        AnswerDomain(
            answerId = this.answerId,
            text = this.text,
//            isRight = this.isRight,
        )

    private fun List<AnswerEntity>.toAnswerDomain(): List<AnswerDomain> =
        map { it.toAnswerDomain() }

    fun ClipEntity.toClipDomain(): ClipDomain =
        ClipDomain(
            clipId = this.clipId,
            text = this.text.clipTextList.toListClipTextDomain(),
            quiz = this.quiz.toQuizDomain(),
            image = this.clipImage
        )

    private fun ClipTextEntity.toClipTextDomain() =
        ClipTextDomain(
            title = this.title,
            text = this.text,
        )

    private fun List<ClipTextEntity>.toListClipTextDomain(): List<ClipTextDomain> =
        map { it.toClipTextDomain() }
}
