package ru.popkov.russeliterature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavEntryPointProvider
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.datastore.settings.Settings
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
internal fun MainScreen(
    settings: Settings? = null,
    navEntryPointProvider: Set<@JvmSuppressWildcards NavEntryPointProvider>,
    bottomNavProviders: Set<@JvmSuppressWildcards NavProvider>,
    navigator: Navigator,
) {
    val mode = settings?.isLightMode?.collectAsState(initial = null)
    RusseLiteratureTheme(
        darkTheme = mode?.value?.isLight ?: false,
    ) {
        MainWindow(
            navEntryPointProvider = navEntryPointProvider,
            bottomNavProviders = bottomNavProviders,
            navigator = navigator,
        )
    }
}
