package ru.popkov.android.core.feature.components.field

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onChange: (text: String) -> Unit = {},
) {
    var search by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    CustomTextField(
        modifier = modifier,
        value = search,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        onValueChanged = {
            search = it
            onChange.invoke(it)
        },
        trailingIcon = {
            if (search.isNotEmpty()) {
                IconButton(onClick = { search = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                    )
                }
            }
        },
        leadingIcon = {
            IconButton(onClick = { search = "" }) {
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White.copy(alpha = 0.6f))
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    SearchField()
}
