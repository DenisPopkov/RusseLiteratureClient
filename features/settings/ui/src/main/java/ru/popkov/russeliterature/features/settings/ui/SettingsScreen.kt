package ru.popkov.russeliterature.features.settings.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularMedium28
import ru.popkov.russeliterature.theme.Grotesk36

@Composable
internal fun SettingsScreen(
    snackbarHostState: SnackbarHostState,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by settingsViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        settingsViewModel.effects
            .collect { effect ->
                when (effect) {

                    else -> {}
                }
            }
    }

    Settings(
        state = state,
    )
}

@Composable
internal fun Settings(
    modifier: Modifier = Modifier,
    state: SettingsState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor)
            .statusBarsPadding()
            .padding(vertical = 30.dp)
            .padding(horizontal = 16.dp),
    ) {
        Text(text = stringResource(id = R.string.settings_title), style = Grotesk36)
        Image(
            modifier = Modifier
                .padding(top = 44.dp)
                .size(size = 160.dp)
                .clip(shape = CircleShape)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = ru.popkov.android.core.feature.ui.R.drawable.ic_article),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = "Фёдор\nДостоевский",
            style = FormularMedium28,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .padding(top = 40.dp),
            text = stringResource(id = R.string.settings_description),
            style = FormularMedium14,
            color = Color.White.copy(alpha = 0.8f)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Colors.InputFieldColor),
            shape = RoundedCornerShape(size = 8.dp),
            onClick = { }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = stringResource(id = R.string.settings_delete_account),
                style = FormularMedium14,
                color = Colors.GrayTextColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    Settings(
        state = SettingsState(),
    )
}
