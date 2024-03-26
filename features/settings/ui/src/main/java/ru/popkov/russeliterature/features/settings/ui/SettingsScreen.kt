package ru.popkov.russeliterature.features.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.theme.Colors

@Composable
internal fun SettingsScreen(
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
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor),
        state = state,
    )
}

@Composable
internal fun Settings(
    modifier: Modifier = Modifier,
    state: SettingsState,
) {
    Column(
        modifier = modifier,
    ) {
        // Header
        // Content
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    Settings(
        state = SettingsState(),
    )
}
