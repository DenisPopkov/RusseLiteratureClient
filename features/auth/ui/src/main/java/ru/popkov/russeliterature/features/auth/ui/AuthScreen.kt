package ru.popkov.russeliterature.features.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.features.auth.ui.components.PasswordField
import ru.popkov.russeliterature.features.auth.ui.components.PhoneNumberField
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.Grotesk36
import ru.popkov.russeliterature.theme.Theme

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
                        snackbarHostState.showSnackbar(effect.errorMessage)

                    AuthViewEffect.GoToMainScreen -> onAuthClick.invoke()
                }
            }
    }

    Auth(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor),
        onPhoneNumberDone = authViewModel::onAction,
        onPasswordDone = authViewModel::onAction,
        onCaptionClick = authViewModel::onAction,
        onActionDone = authViewModel::onAction,
    )
}

@Composable
private fun Auth(
    state: AuthFormState,
    modifier: Modifier = Modifier,
    onPhoneNumberDone: (AuthViewAction) -> Unit = {},
    onPasswordDone: (AuthViewAction) -> Unit = {},
    onCaptionClick: (AuthViewAction) -> Unit = {},
    onActionDone: (AuthViewAction) -> Unit = {},
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
                    .padding(top = 84.dp),
                text = stringResource(id = R.string.auth_title),
                style = Grotesk36,
            )

            when (state.authGlobalState) {
                AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER -> {
                    PhoneNumberField(
                        modifier = Modifier.padding(top = 72.dp),
                        onPhoneDone = onActionDone,
                        onPhoneNumberChange = onPhoneNumberDone,
                    )
                }

                AuthGlobalState.REGISTER_NEW_USER_PASSWORD -> {
                    PasswordField(
                        modifier = Modifier.padding(top = 72.dp),
                        onPasswordDone = onActionDone,
                        onPasswordChange = onPasswordDone,
                    )
                }

                AuthGlobalState.AUTH -> {
                    PhoneNumberField(
                        modifier = Modifier.padding(top = 72.dp),
                        onPhoneDone = onActionDone,
                        onPhoneNumberChange = onPhoneNumberDone,
                    )
                    PasswordField(
                        modifier = Modifier.padding(top = 18.dp),
                        onPasswordDone = onActionDone,
                        onPasswordChange = onPasswordDone,
                    )
                }
            }

            val clickAction = when (state.authGlobalState) {
                AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER, AuthGlobalState.REGISTER_NEW_USER_PASSWORD -> AuthViewAction.OnAlreadyHaveAccountClick
                AuthGlobalState.AUTH -> AuthViewAction.OnNoAccountClick
            }

            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 2.dp)
                    .clickable { onCaptionClick.invoke(clickAction) },
                text = stringResource(
                    id = when (state.authGlobalState) {
                        AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER, AuthGlobalState.REGISTER_NEW_USER_PASSWORD -> R.string.auth_already_have_account
                        AuthGlobalState.AUTH -> R.string.auth_no_account
                    }
                ),
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