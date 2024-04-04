package ru.popkov.russeliterature.features.home.ui

internal sealed interface HomeViewEffect {

    data class ShowError(val errorMessage: String) : HomeViewEffect
}