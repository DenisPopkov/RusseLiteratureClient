package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class AnswerWrapper(val answerList: List<Answer>)

@Entity
data class Quiz(
    @PrimaryKey
    val quizId: Long,
    val question: String,
    val description: String,
    val image: String,
    @Embedded
    val answers: AnswerWrapper,
)
