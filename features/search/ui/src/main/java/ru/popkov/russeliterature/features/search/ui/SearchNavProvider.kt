package ru.popkov.russeliterature.features.search.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.search.nav.SearchDestination
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class SearchNavProvider @Inject constructor(
    private val navigator: Navigator,
) : NavProvider {

    override val navBarItem = NavProvider.BottomBarItem(
        index = 1,
        labelResId = ru.popkov.russeliterature.features.search.nav.R.string.search_bottomnav_label,
        icon = Icons.Outlined.Search,
        route = SearchDestination.route,
        isStart = false,
    )

    override fun graph(builder: NavGraphBuilder, snackbarHostState: SnackbarHostState) =
        builder.run {
            composable(
                route = SearchDestination.route,
            ) {
                SearchScreen(
                    snackbarHostState = snackbarHostState,
                )
            }
        }

}