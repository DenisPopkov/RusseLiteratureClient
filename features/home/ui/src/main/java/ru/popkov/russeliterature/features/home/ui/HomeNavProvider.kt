package ru.popkov.russeliterature.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.home.nav.HomeDestination
import ru.popkov.russeliterature.features.home.nav.R
import ru.popkov.russeliterature.theme.Colors
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
            composable(
                route = HomeDestination.route,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Colors.BackgroundColor),
                ) {

                }
            }
        }

}