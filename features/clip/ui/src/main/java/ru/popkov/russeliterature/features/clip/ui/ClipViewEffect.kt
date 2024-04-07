package ru.popkov.russeliterature.features.clip.ui

internal sealed interface ClipViewEffect {
    data object OnToQuizEffect : ClipViewEffect
    data class ShowError(val errorMessage: String) : ClipViewEffect
}