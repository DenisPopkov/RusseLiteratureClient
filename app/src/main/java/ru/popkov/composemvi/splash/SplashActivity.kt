package ru.popkov.composemvi.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.popkov.composemvi.MainActivity
import ru.popkov.composemvi.theme.Theme
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SplashScreen()

                    // navigate to main screen after small delay
                    Executors.newSingleThreadScheduledExecutor().schedule({
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }, 3, TimeUnit.SECONDS)
                }
            }
        }
    }

}