package ru.popkov.russeliterature.features.clip.ui

sealed interface ClipViewAction {
    data class OnToQuizClick(val quizId: Long) : ClipViewAction
}