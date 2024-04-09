package ru.popkov.android.core.feature.components.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.popkov.android.core.feature.components.core.models.SectionFilterItem
import ru.popkov.android.core.feature.components.core.models.SectionType
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.Grotesk14
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
fun SectionFilter(
    modifier: Modifier = Modifier,
    sectionItem: SectionFilterItem,
    onSectionFilterClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.clickable { onSectionFilterClick.invoke() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = sectionItem.sectionType.sectionName,
            style = Grotesk14,
            color = if (sectionItem.isSectionSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary,
        )

        AnimatedVisibility(visible = sectionItem.isSectionSelected) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(width = sectionItem.sectionType.sectionName.calculateUnderLineWidth()),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

private fun String.calculateUnderLineWidth() = (this.length * 8).dp

@UiModePreviews
@Composable
private fun Preview() {
    RusseLiteratureTheme {
        SectionFilter(
            sectionItem = SectionFilterItem(
                sectionType = SectionType.ALL,
                isSectionSelected = true,
            ),
        )
    }
}