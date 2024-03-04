package ru.popkov.russeliterature.features.splash.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.StartNavProvider
import ru.popkov.russeliterature.features.auth.nav.AuthDestination
import ru.popkov.russeliterature.features.splash.nav.SplashDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class SplashNavProvider @Inject constructor(
    private val navigator: Navigator,
) : StartNavProvider {

    override val routeItem = StartNavProvider.RouteItem(
        route = SplashDestination.route,
    )

    override fun graph(builder: NavGraphBuilder) =
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