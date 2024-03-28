package ru.popkov.russeliterature.features.quiz.ui

sealed interface QuizViewAction {
    data object OnAnswerClick : QuizViewAction
}