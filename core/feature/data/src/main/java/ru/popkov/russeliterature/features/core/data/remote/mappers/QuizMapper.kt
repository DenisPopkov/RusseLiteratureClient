package ru.popkov.russeliterature.features.core.data.remote.mappers

import ru.popkov.russeliterature.features.core.data.local.entities.AnswerWrapper
import ru.popkov.russeliterature.features.core.data.local.entities.ClipTextWrapper
import ru.popkov.russeliterature.features.core.data.local.entities.Answer as AnswerEntity
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText as ClipTextEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Clips as ClipEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz as QuizEntity
import ru.popkov.russeliterature.features.core.data.remote.dtos.Answer as AnswerDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Clip as ClipDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.ClipText as ClipTextDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Quiz as QuizDto

object QuizMapper {

    fun QuizDto.toQuizEntity(): QuizEntity =
        QuizEntity(
            quizId = this.id,
            image = this.image,
            question = this.question,
            description = this.description,
            answers = AnswerWrapper(answerList = this.answers.map { it.toAnswerEntity() }),
        )

    fun AnswerDto.toAnswerEntity(): AnswerEntity =
        AnswerEntity(
            answerId = this.id,
            text = this.text,
//            isRight = this.isRight,
        )

    fun List<AnswerDto>.toListAnswerEntity(): List<AnswerEntity> =
        map { it.toAnswerEntity() }

    fun ClipDto.toClipEntity(): ClipEntity =
        ClipEntity(
            clipId = this.id,
            text = ClipTextWrapper(clipTextList = this.clipTexts.toListClipTextEntity()),
            quiz = this.quiz.toQuizEntity(),
            clipImage = this.image,
        )

    fun List<ClipTextDto>.toListClipTextEntity(): List<ClipTextEntity> =
        map { it.toClipTextEntity() }

    private fun ClipTextDto.toClipTextEntity(): ClipTextEntity =
        ClipTextEntity(
            id = 0L,
            title = this.title,
            text = this.text,
        )
}