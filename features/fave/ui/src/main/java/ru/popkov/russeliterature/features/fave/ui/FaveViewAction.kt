package ru.popkov.russeliterature.features.fave.ui

sealed interface FaveViewAction {
    data object OnMainScreenClick : FaveViewAction
}