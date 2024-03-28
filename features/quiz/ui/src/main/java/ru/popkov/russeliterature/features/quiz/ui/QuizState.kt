package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class QuizState(
    val quizItems: List<QuizItem> = emptyList(),
)
