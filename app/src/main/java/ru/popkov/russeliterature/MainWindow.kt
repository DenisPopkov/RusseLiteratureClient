package ru.popkov.russeliterature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.popkov.android.core.feature.nav.NavigationLaunchedEffect
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavEntryPointProvider
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.auth.ui.AuthViewModel

@Composable
fun MainWindow(
    navEntryPointProvider: Set<NavEntryPointProvider>,
    bottomNavProviders: Set<NavProvider>,
    navigator: Navigator,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isUserAuth = authViewModel.isUserLogged.collectAsState()
    val entryPointItems = navEntryPointProvider
        .mapNotNull { it.routeItem }
        .sortedBy { it.isStart }
    val bottomBarItems = bottomNavProviders
        .mapNotNull { it.navBarItem }
        .sortedBy { it.index }
    val snackbarHostState = remember { SnackbarHostState() }

    navController.addOnDestinationChangedListener { _, _, _ ->
        authViewModel.checkUser()
    }

    NavigationLaunchedEffect(
        navigator = navigator,
        navController = navController,
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            AnimatedVisibility(visible = isUserAuth.value) {
                MainNavBar(
                    items = bottomBarItems,
                    navController = navController,
                )
            }
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = entryPointItems.find { it.isStart }?.route ?: "",
            modifier = Modifier
                .padding(innerPadding),
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