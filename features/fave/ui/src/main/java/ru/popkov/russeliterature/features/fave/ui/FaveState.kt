package ru.popkov.russeliterature.features.fave.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class FaveState(
    val faveList: List<Any>? = null,
)
