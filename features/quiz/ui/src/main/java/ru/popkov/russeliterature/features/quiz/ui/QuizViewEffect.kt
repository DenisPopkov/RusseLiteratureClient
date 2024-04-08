package ru.popkov.russeliterature.features.quiz.ui

internal sealed interface QuizViewEffect {
    data object OnCloseEffect: QuizViewEffect
    data class ShowError(val errorMessage: String): QuizViewEffect
}