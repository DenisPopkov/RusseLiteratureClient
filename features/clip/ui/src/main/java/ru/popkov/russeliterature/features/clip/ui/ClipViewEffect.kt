package ru.popkov.russeliterature.features.clip.ui

internal sealed interface ClipViewEffect {
    data class OnToQuizEffect(val quizId: Long) : ClipViewEffect
    data class ShowError(val errorMessage: String) : ClipViewEffect
}