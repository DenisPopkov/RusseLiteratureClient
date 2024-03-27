package ru.popkov.russeliterature.features.clip.ui

sealed interface ClipViewAction {
    data object OnFaveClick : ClipViewAction
}