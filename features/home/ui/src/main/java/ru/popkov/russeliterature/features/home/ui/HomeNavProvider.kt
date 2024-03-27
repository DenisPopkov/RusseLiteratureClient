package ru.popkov.russeliterature.features.home.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.clip.ui.ClipDestination
import ru.popkov.russeliterature.features.clip.ui.ClipScreen
import ru.popkov.russeliterature.features.home.nav.HomeDestination
import ru.popkov.russeliterature.features.home.nav.R
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class HomeNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavProvider {

    override val navBarItem = NavProvider.BottomBarItem(
        index = 0,
        labelResId = R.string.home_bottomnav_label,
        icon = Icons.Outlined.Home,
        route = HomeDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            navigation(
                startDestination = HomeDestination.route,
                route = "clipy",
            ) {
                composable(
                    route = HomeDestination.route,
                ) {
                    HomeScreen(
                        snackbarHostState = snackbarHostState,
                        onCardClick = {
                            navigator.navigate(ClipDestination)
                        }
                    )
                }
                composable(
                    route = ClipDestination.route,
                ) {
                    ClipScreen(
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }

}