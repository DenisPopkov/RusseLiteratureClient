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
import ru.popkov.russeliterature.features.auth.ui.utils.CODE_DIGIT
import ru.popkov.russeliterature.features.auth.ui.utils.MASK_NUMBER
import ru.popkov.russeliterature.features.auth.ui.utils.PHONE_NUMBER_MASK
import ru.popkov.russeliterature.theme.Theme

@Composable
fun PhoneNumberField(
    modifier: Modifier = Modifier,
    onPhoneNumberDone: (AuthViewAction) -> Unit = {},
) {
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    CustomTextField(
        modifier = modifier,
        value = phoneNumber,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        keyboardActions = KeyboardActions(onDone = {
            onPhoneNumberDone.invoke(AuthViewAction.OnApplyPhoneNumberClick(CODE_DIGIT + phoneNumber))
            keyboardController?.hide()
        }),
        placeHolderText = R.string.auth_phone,
        mask = PHONE_NUMBER_MASK,
        maskNumber = MASK_NUMBER,
        onValueChanged = { phoneNumber = it },
        trailingIcon = {
            if (phoneNumber.isNotEmpty()) {
                IconButton(onClick = { phoneNumber = "" }) {
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
            PhoneNumberField()
        }
    }
}