package ru.popkov.android.core.feature.components.core.models

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CardType(val height: Dp, val width: Dp) {
    SMALL(height = 100.dp, width = 100.dp),
    MEDIUM(height = 160.dp, width = 160.dp),
    LARGE(height = 170.dp, width = 250.dp),
    ;
}