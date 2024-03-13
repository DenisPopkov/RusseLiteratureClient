package ru.popkov.russeliterature.features.auth.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ru.popkov.russeliterature.features.auth.ui.AuthViewAction
import ru.popkov.russeliterature.features.auth.ui.R
import ru.popkov.russeliterature.theme.Theme

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    onPasswordDone: (AuthViewAction) -> Unit = {},
    onPasswordChange: (AuthViewAction) -> Unit = {},
) {
    var password by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    CustomTextField(
        modifier = modifier,
        value = password,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(onDone = {
            onPasswordDone.invoke(AuthViewAction.OnDone)
            keyboardController?.hide()
        }),
        placeHolderText = R.string.auth_password,
        onValueChanged = {
            password = it
            onPasswordChange.invoke(AuthViewAction.OnPasswordChange(password))
        },
        trailingIcon = {
            if (password.isNotEmpty()) {
                IconButton(onClick = { password = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    Theme {
        Surface {
            PasswordField()
        }
    }
}