package ru.popkov.android.core.feature.components.core

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Grotesk20

@Composable
fun SectionHeader(
    @StringRes sectionText: Int,
    onSectionClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSectionClick.invoke() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_section_arrow),
            contentDescription = "To section",
            colorFilter = ColorFilter.tint(color = Color.White.copy(alpha = 0.8f))
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = sectionText),
            style = Grotesk20,
            color = Color.White.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun Preview() {
    SectionHeader(
        sectionText = R.string.section_title,
    )
}