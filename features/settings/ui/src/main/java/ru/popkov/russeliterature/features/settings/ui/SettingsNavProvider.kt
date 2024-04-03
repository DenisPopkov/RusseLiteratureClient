package ru.popkov.russeliterature.features.settings.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.features.auth.nav.AuthDestination
import ru.popkov.russeliterature.features.settings.nav.SettingsDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class SettingsNavProvider @Inject constructor(
    private val navigator: Navigator,
    private val userDataStore: User,
) : NavProvider {

    override val navBarItem = NavProvider.BottomBarItem(
        index = 3,
        labelResId = ru.popkov.russeliterature.features.settings.nav.R.string.settings_bottomnav_label,
        icon = Icons.Outlined.Settings,
        route = SettingsDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(
                route = SettingsDestination.route,
            ) {
                SettingsScreen(
                    snackbarHostState = snackbarHostState,
                    userDataStore = userDataStore,
                    onDeleteAccountClick = {
                        navigator.navigate(AuthDestination) {
                            launchSingleTop = true
                            popUpTo(0)
                        }
                    }
                )
            }
        }

}