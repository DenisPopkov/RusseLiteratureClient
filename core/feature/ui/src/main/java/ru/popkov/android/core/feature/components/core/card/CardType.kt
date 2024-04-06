package ru.popkov.android.core.feature.components.core.card

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CardType(val height: Dp, val width: Dp) {
    SMALL(height = 100.dp, width = 100.dp),
    MEDIUM(height = 160.dp, width = 160.dp),
    LARGE(height = 170.dp, width = 300.dp),
    ;
}

sealed class CardAction {
    data class OnCardClick(val cardId: Long): CardAction()
    data class OnFaveClick(val cardId: Long): CardAction()
}