package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun AuthScreen(
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = hiltViewModel(),
    onAuthClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.clickable {
                authViewModel.loginUser().also { onAuthClick.invoke() }
            },
            text = "AUTH",
            fontSize = 100.sp
        )
    }
}