package ru.popkov.composemvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.popkov.android.core.feature.nav.Navigator
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.android.core.feature.ui.StartNavProvider
import ru.popkov.composemvi.theme.Theme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navProviders: Set<@JvmSuppressWildcards NavProvider>

    @Inject
    lateinit var startProviders: Set<@JvmSuppressWildcards StartNavProvider>

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainWindow(
                        startNavProviders = startProviders,
                        navProviders = navProviders,
                        navigator = navigator,
                    )
                }
            }
        }
    }

}