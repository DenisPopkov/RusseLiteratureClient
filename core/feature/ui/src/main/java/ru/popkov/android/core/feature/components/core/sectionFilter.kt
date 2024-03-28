package ru.popkov.android.core.feature.components.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.Grotesk14

data class SectionFilterItem(
    val sectionName: String,
    val isSectionSelected: Boolean = false,
)

@Composable
fun SectionFilter(
    modifier: Modifier = Modifier,
    sectionFilterListener: List<SectionFilterItem>,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Colors.BackgroundColor),
        horizontalArrangement = Arrangement.spacedBy(space = 18.dp),
    ) {
        items(sectionFilterListener) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = it.sectionName,
                    style = Grotesk14,
                    color = if (it.isSectionSelected) Color.White else Colors.GrayTextColor,
                )

                AnimatedVisibility(visible = it.isSectionSelected) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .width(width = it.sectionName.calculateUnderLineWidth()),
                        thickness = 1.dp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

private fun String.calculateUnderLineWidth() = (this.length * 8).dp

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SectionFilter(
        sectionFilterListener = listOf(
            SectionFilterItem(
                sectionName = "все",
                isSectionSelected = true,
            ),
            SectionFilterItem(
                sectionName = "писатели",
            ),
            SectionFilterItem(
                sectionName = "статьи",
            ),
            SectionFilterItem(
                sectionName = "поэты",
            ),
        )
    )
}