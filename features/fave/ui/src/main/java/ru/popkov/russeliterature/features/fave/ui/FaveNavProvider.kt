package ru.popkov.russeliterature.features.fave.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.clip.ui.ClipDestination
import ru.popkov.russeliterature.features.fave.nav.FaveDestination
import ru.popkov.russeliterature.features.home.nav.HomeDestination
import ru.popkov.russeliterature.features.section.ui.SectionDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class FaveNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavProvider {

    override val navBarItem = NavProvider.BottomBarItem(
        index = 2,
        labelResId = ru.popkov.russeliterature.features.fave.nav.R.string.fave_bottomnav_label,
        icon = Icons.Outlined.FavoriteBorder,
        route = FaveDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(
                route = FaveDestination.route,
            ) {
                FaveScreen(
                    snackbarHostState = snackbarHostState,
                    onCardClick = {
                        navigator.navigate(ClipDestination(it))
                    },
                    onGoMainScreen = {
                        navigator.navigate(HomeDestination) {
                            launchSingleTop = true
                            popUpTo(0)
                        }
                    },
                    onSectionClick = {
                        navigator.navigate(SectionDestination(it))
                    }
                )
            }
        }

}