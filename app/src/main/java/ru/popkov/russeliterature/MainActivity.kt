package ru.popkov.russeliterature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavEntryPointProvider
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.datastore.settings.Settings
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomNavProviders: Set<@JvmSuppressWildcards NavProvider>

    @Inject
    lateinit var navEntryPointProvider: Set<@JvmSuppressWildcards NavEntryPointProvider>

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var settingsStore: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainScreen(
                settings = settingsStore,
                navEntryPointProvider = navEntryPointProvider,
                bottomNavProviders = bottomNavProviders,
                navigator = navigator
            )
        }
    }

}