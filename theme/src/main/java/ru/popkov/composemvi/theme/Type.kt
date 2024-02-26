package ru.popkov.composemvi.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.popkov.composesample.theme.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val GothicBoldSplash40 = TextStyle(
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 50.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.gothic_bold)),
)