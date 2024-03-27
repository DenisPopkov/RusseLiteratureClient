package ru.popkov.android.core.feature.components.core

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.FormularMedium12

@Composable
fun Section(
    modifier: Modifier = Modifier,
    @StringRes sectionText: Int,
    onSectionClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSectionClick.invoke() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = sectionText),
            style = FormularMedium12,
            color = Color.White.copy(alpha = 0.8f)
        )
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "To section",
            colorFilter = ColorFilter.tint(color = Color.White.copy(alpha = 0.8f))
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Section(
        sectionText = R.string.section_author,
    )
}