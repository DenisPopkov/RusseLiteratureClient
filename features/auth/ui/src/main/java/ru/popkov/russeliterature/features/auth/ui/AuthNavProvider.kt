package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.LoginNavProvider
import ru.popkov.composemvi.features.spotlight.nav.SpotlightDestination
import ru.popkov.russeliterature.features.auth.nav.AuthDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class AuthNavProvider @Inject constructor(
    private val navigator: Navigator,
) : LoginNavProvider {

    override val routeItem = LoginNavProvider.RouteItem(
        route = AuthDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(
                route = AuthDestination.route,
            ) {
                AuthScreen(
                    snackbarHostState = snackbarHostState,
                    onAuthClick = {
                        navigator.navigate(SpotlightDestination) {
                            launchSingleTop = true
                            popUpTo(0)
                        }
                    },
                )
            }
        }

}