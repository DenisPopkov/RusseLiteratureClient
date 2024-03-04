package ru.popkov.android.core.feature.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder

interface StartNavProvider {

    data class RouteItem(
        val route: String,
        val isStart: Boolean,
    )

    val routeItem: RouteItem?

    fun graph(
        builder: NavGraphBuilder,
        snackbarHostState: SnackbarHostState,
    )

}