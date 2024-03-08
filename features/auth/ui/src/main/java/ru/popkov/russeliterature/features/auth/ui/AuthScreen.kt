package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.Grotesk36
import ru.popkov.russeliterature.theme.Theme

@ExperimentalMaterial3Api
@Composable
internal fun AuthScreen(
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = hiltViewModel(),
    onAuthClick: () -> Unit,
) {

    val state by authViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.effects
            .collect { effect ->
                when (effect) {
                    is AuthViewEffect.ShowError ->
                        snackbarHostState.showSnackbar("Validation failed")
                    is AuthViewEffect.ChangeAuthToAlreadyHaveAccount -> {}
                    is AuthViewEffect.ChangeAuthToNoAccount -> {}
                    is AuthViewEffect.ValidateField -> {}
                }
            }
    }

    Auth(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding(),
        onAuthClick = authViewModel::onAction,
    )
}

@Composable
private fun Auth(
    state: AuthFormState,
    modifier: Modifier = Modifier,
    onAuthClick: (AuthViewAction) -> Unit = {},
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_auth_back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 42.dp),
                text = stringResource(id = R.string.auth_title),
                style = Grotesk36,
            )

            CustomTextField(
                modifier = Modifier.padding(top = 72.dp),
                maxLength = 11,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeHolderText = R.string.auth_phone,
            )

            CustomTextField(
                modifier = Modifier.padding(top = 18.dp),
                maxLength = 18,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeHolderText = R.string.auth_password,
            )

            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clickable { },
                text = stringResource(id = R.string.auth_already_have_account),
                style = FormularRegular14,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Theme {
        Surface {
            Auth(state = AuthFormState())
        }
    }
}