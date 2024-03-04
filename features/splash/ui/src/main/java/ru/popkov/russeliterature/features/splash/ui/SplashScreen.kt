package ru.popkov.russeliterature.features.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.composemvi.theme.GothicBoldSplash40
import ru.popkov.composemvi.theme.Theme

@Composable
internal fun SplashScreen(
    onDelayHandle: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Splash screen image",
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(bottom = Theme.size.medium)
                .padding(start = Theme.size.medium),
            text = stringResource(id = R.string.splash_screen_label),
            style = GothicBoldSplash40,
        )
    }

    // navigate to main screen after small delay
    LaunchedEffect(Unit) {
        delay(2000)
        onDelayHandle.invoke()
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    Theme {
        Surface {
            SplashScreen()
        }
    }
}