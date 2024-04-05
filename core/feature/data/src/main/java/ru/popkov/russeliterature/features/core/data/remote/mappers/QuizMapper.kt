package ru.popkov.russeliterature.features.core.data.remote.mappers

import ru.popkov.russeliterature.features.core.data.local.entities.Answer as AnswerEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Clip as ClipEntity
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText as ClipTextEntity
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz as QuizEntity
import ru.popkov.russeliterature.features.core.data.remote.dtos.Answer as AnswerDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Clip as ClipDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.ClipText as ClipTextDto
import ru.popkov.russeliterature.features.core.data.remote.dtos.Quiz as QuizDto

object QuizMapper {

    fun QuizDto.toQuizEntity(): QuizEntity =
        QuizEntity(
            id = this.id,
            image = this.image,
            question = this.question,
            description = this.description,
            answers = this.answers.toListAnswerEntity(),
        )

    fun AnswerDto.toAnswerEntity(): AnswerEntity =
        AnswerEntity(
            id = this.id,
            text = this.text,
            isRight = this.isRight,
        )

    fun List<AnswerDto>.toListAnswerEntity(): List<AnswerEntity> =
        map { it.toAnswerEntity() }

    fun ClipDto.toClipEntity(): ClipEntity =
        ClipEntity(
            id = this.id,
            text = this.text.toListClipTextEntity(),
            quiz = this.quiz,
        )

    private fun List<ClipTextDto>.toListClipTextEntity(): List<ClipTextEntity> =
        map { it.toClipTextEntity() }

    private fun ClipTextDto.toClipTextEntity() =
        ClipTextEntity(
            id = 0L,
            title = this.title,
            text = this.text,
        )
}