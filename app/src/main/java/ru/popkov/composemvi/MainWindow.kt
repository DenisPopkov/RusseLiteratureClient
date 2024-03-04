package ru.popkov.composemvi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.popkov.android.core.feature.nav.NavigationLaunchedEffect
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.LoginNavProvider
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.android.core.feature.ui.StartNavProvider
import ru.popkov.russeliterature.features.auth.ui.AuthViewModel
import timber.log.Timber

@Composable
fun MainWindow(
    splashNavProvider: StartNavProvider,
    authNavProvider: LoginNavProvider,
    bottomNavProviders: Set<NavProvider>,
    navigator: Navigator,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    var isUserAuth by remember {
        mutableStateOf(false)
    }
    val bottomBarItems = bottomNavProviders
        .mapNotNull { it.navBarItem }
        .sortedBy { it.index }
    val snackbarHostState = remember { SnackbarHostState() }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        Timber.tag("efefe").d("destination - %s", destination)
        isUserAuth = destination.route == "spotlight"
    }

    NavigationLaunchedEffect(
        navigator = navigator,
        navController = navController,
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
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

        NavHost(
            navController = navController,
            startDestination = splashNavProvider.routeItem.route,
            modifier = Modifier
                .padding(innerPadding),
        ) {
            // graph below for bottom bar navigation flow
            splashNavProvider.graph(this)
            // graph below for auth navigation flow
            authNavProvider.graph(this, snackbarHostState)
            // graph below for bottom bar navigation flow
            bottomNavProviders.forEach { subGraph ->
                subGraph.graph(this, snackbarHostState)
            }
        }
    }
}