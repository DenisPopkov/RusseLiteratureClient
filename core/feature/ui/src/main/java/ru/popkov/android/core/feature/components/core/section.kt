package ru.popkov.android.core.feature.components.core

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import ru.popkov.android.core.feature.ui.R
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.FormularMedium12
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

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
            color = MaterialTheme.colorScheme.onSurface,
        )
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "To section",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
        )
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    RusseLiteratureTheme {
        Section(
            sectionText = R.string.section_author,
        )
    }
}