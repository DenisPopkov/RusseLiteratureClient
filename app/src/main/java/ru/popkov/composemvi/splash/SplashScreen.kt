package ru.popkov.composemvi.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.composemvi.theme.GothicBoldSplash40
import ru.popkov.composemvi.theme.Theme
import ru.popkov.composesample.R

@Composable
internal fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Splash screen image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = Theme.size.medium)
                .padding(start = Theme.size.medium),
            text = stringResource(id = R.string.splash_screen_label),
            style = GothicBoldSplash40,
        )
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