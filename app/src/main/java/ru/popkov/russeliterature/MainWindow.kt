package ru.popkov.russeliterature

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.popkov.android.core.feature.nav.NavigationLaunchedEffect
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavEntryPointProvider
import ru.popkov.android.core.feature.ui.NavProvider

@Composable
fun MainWindow(
    navEntryPointProvider: Set<NavEntryPointProvider>,
    bottomNavProviders: Set<NavProvider>,
    navigator: Navigator,
) {
    val navController = rememberNavController()
    val entryPointItems = navEntryPointProvider
        .mapNotNull { it.routeItem }
        .sortedBy { it.isStart }
    val bottomBarItems = bottomNavProviders
        .mapNotNull { it.navBarItem }
        .sortedBy { it.index }
    val snackbarHostState = remember { SnackbarHostState() }

    var showBottomNavBar by remember {
        mutableStateOf(false)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        showBottomNavBar =
            destination.route == "home" || destination.route == "search" || destination.route == "fave" || destination.route == "settings"
    }

    NavigationLaunchedEffect(
        navigator = navigator,
        navController = navController,
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(paddingValues = WindowInsets.ime.asPaddingValues()),
            ) {
                Snackbar(
                    containerColor = Color.Red,
                    snackbarData = it,
                )
            }
        },
        bottomBar = {
            if (showBottomNavBar) {
                MainNavBar(
                    items = bottomBarItems,
                    navController = navController,
                )
            }
        },
    ) { innerPadding ->

        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = entryPointItems.find { it.isStart }?.route ?: "",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            // graph below for entry point navigation flow
            // like splash and auth screens
            navEntryPointProvider.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
            // graph below for bottom bar navigation flow
            bottomNavProviders.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
        }
    }
}