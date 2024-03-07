package ru.popkov.russeliterature.features.splash.ui

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavEntryPointProvider
import ru.popkov.russeliterature.features.auth.nav.AuthDestination
import ru.popkov.russeliterature.features.splash.nav.SplashDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class SplashNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavEntryPointProvider {

    override val routeItem = NavEntryPointProvider.RouteItem(
        route = SplashDestination.route,
        isStart = true,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(
                route = SplashDestination.route,
            ) {
                SplashScreen(
                    onDelayHandle = {
                        navigator.navigate(AuthDestination) {
                            launchSingleTop = true
                        }
                    },
                )
            }
        }

}