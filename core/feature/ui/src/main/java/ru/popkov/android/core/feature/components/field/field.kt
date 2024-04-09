package ru.popkov.android.core.feature.components.field

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.popkov.android.core.feature.ui.R
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    keyboardActions: KeyboardActions = KeyboardActions(),
    inputFieldTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    inputFieldColor: Color = MaterialTheme.colorScheme.primaryContainer,
    @StringRes placeHolderText: Int = R.string.search_hint,
    onValueChanged: (String) -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
) {

    Column(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = inputFieldColor,
                unfocusedContainerColor = inputFieldColor,
                disabledContainerColor = inputFieldColor,
                focusedTextColor = inputFieldTextColor,
                unfocusedTextColor = inputFieldTextColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                cursorColor = inputFieldTextColor,
            ),
            onValueChange = { value ->
                onValueChanged(value)
            },
            shape = RoundedCornerShape(size = 16.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = placeHolderText),
                    style = FormularMedium14,
                    color = inputFieldTextColor
                )
            },
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            textStyle = FormularMedium14,
        )
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    RusseLiteratureTheme {
        CustomTextField()
    }
}
