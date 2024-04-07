package ru.popkov.russeliterature.features.clip.ui

sealed interface ClipViewAction {
    data object OnToQuizClick : ClipViewAction
}