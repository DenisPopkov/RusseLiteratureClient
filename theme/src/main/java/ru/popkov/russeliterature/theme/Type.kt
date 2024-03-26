package ru.popkov.russeliterature.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val GothicBold44 = TextStyle(
    fontSize = 44.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 50.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.gothic_bold)),
)

val GothicBoldSplash40 = TextStyle(
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 50.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.gothic_bold)),
)

val Grotesk36 = TextStyle(
    fontSize = 36.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 50.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.grotesk_bold)),
)

val Grotesk20 = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.grotesk_bold)),
)

val Grotesk14 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.grotesk_bold)),
)

val FormularMedium20 = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.formular_medium)),
)

val FormularRegular14 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.formular_regular)),
)

val FormularMedium14 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.formular_medium)),
)

val FormularMedium12 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 22.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.formular_medium)),
)

val FormularRegular12 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
    color = Color.White,
    fontFamily = FontFamily(Font(R.font.formular_regular)),
)
