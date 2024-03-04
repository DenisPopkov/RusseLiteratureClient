package ru.popkov.composemvi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.popkov.android.core.feature.nav.NavigationLaunchedEffect
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.android.core.feature.ui.StartNavProvider

@Composable
fun MainWindow(
    startNavProviders: Set<StartNavProvider>,
    navProviders: Set<NavProvider>,
    navigator: Navigator,
) {
    val navController = rememberNavController()
    val isUserAuth = false // TODO() use viewmodel to store this global flag
    val bottomBarItems = navProviders
        .mapNotNull { it.navBarItem }
        .sortedBy { it.index }
    val authItems = startNavProviders
        .mapNotNull { it.routeItem }
    val snackbarHostState = remember { SnackbarHostState() }

    NavigationLaunchedEffect(
        navigator = navigator,
        navController = navController,
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            AnimatedVisibility(visible = isUserAuth) {
                MainNavBar(
                    items = bottomBarItems,
                    navController = navController,
                )
            }
        },
    ) { innerPadding ->

        val startDestination = when {
            !isUserAuth -> (authItems.firstOrNull { it.isStart } ?: authItems.first()).route
            else -> (bottomBarItems.firstOrNull { it.isStart } ?: bottomBarItems.first()).route
        }

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            // graph below for bottom bar navigation flow
            navProviders.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
            // graph below for auth navigation flow
            startNavProviders.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
        }
    }
}