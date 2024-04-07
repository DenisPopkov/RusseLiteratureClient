package ru.popkov.russeliterature.features.home.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.features.clip.ui.ClipDestination
import ru.popkov.russeliterature.features.clip.ui.ClipScreen
import ru.popkov.russeliterature.features.home.nav.HomeDestination
import ru.popkov.russeliterature.features.home.nav.R
import ru.popkov.russeliterature.features.quiz.ui.QuizDestination
import ru.popkov.russeliterature.features.quiz.ui.QuizScreen
import ru.popkov.russeliterature.features.section.ui.SectionDestination
import ru.popkov.russeliterature.features.section.ui.SectionScreen
import se.ansman.dagger.auto.AutoBindIntoSet
import javax.inject.Inject

@AutoBindIntoSet
class HomeNavProvider @Inject constructor(
    private val navigator: Navigator,
    private val userDatastore: User,
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
                        userDataStore = userDatastore,
                        onCardClick = {
                            navigator.navigate(ClipDestination(it))
                        },
                        onSectionClick = {
                            navigator.navigate(SectionDestination(it))
                        }
                    )
                }
                composable(
                    route = ClipDestination.route,
                    arguments = ClipDestination.args,
                ) {
                    ClipScreen(
                        snackbarHostState = snackbarHostState,
                        userDataStore = userDatastore,
                        onToQuizClick = {
                            navigator.navigate(QuizDestination(it))
                        }
                    )
                }
                composable(
                    route = QuizDestination.route,
                    arguments = QuizDestination.args,
                ) {
                    QuizScreen(
                        snackbarHostState = snackbarHostState,
                        onCloseClick = {
                            navigator.navigate(HomeDestination)
                        }
                    )
                }
                composable(
                    route = SectionDestination.route,
                    arguments = SectionDestination.args
                ) {
                    SectionScreen(
                        snackbarHostState = snackbarHostState,
                        userDataStore = userDatastore,
                        onBackClick = {
                            navigator.onBackClick()
                        }
                    )
                }
            }
        }

}