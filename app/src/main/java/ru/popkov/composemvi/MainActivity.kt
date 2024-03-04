package ru.popkov.composemvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.LoginNavProvider
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.android.core.feature.ui.StartNavProvider
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomNavProviders: Set<@JvmSuppressWildcards NavProvider>

    @Inject
    lateinit var authProvider: Set<@JvmSuppressWildcards LoginNavProvider>

    @Inject
    lateinit var splashProvider: Set<@JvmSuppressWildcards StartNavProvider>

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        enableEdgeToEdge()
        setContent {
            MainWindow(
                splashNavProvider = splashProvider.first(),
                authNavProvider = authProvider.first(),
                bottomNavProviders = bottomNavProviders,
                navigator = navigator,
            )
        }
    }

}