package ru.popkov.russeliterature.features.fave.ui

internal sealed interface FaveViewEffect {
    data object ShowEmptyState : FaveViewEffect
    data object GoToMainScreen : FaveViewEffect

}