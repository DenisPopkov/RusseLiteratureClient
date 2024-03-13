package ru.popkov.russeliterature.features.splash.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                },
            ) {
                SplashScreen(
                    onDelayHandle = {
                        navigator.navigate(AuthDestination) {
                            launchSingleTop = true
                            popUpTo(0)
                        }
                    },
                )
            }
        }

}