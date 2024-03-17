package ru.popkov.russeliterature.features.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.features.search.nav.SearchDestination
import ru.popkov.russeliterature.theme.Colors
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Colors.BackgroundColor),
                ) {

                }
            }
        }

}