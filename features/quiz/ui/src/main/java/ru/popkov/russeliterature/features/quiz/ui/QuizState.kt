package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.runtime.Immutable

enum class Quiz {
    QUESTION,
    RESULTS,
    ;
}

@Immutable
internal data class QuizState(
    val item: QuizItem = QuizItem(),
    val quiz: Quiz = Quiz.QUESTION,
)
