package ru.popkov.russeliterature.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ru.popkov.russeliterature.theme.Colors.Pink40
import ru.popkov.russeliterature.theme.Colors.Pink80
import ru.popkov.russeliterature.theme.Colors.Purple40
import ru.popkov.russeliterature.theme.Colors.Purple80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    tertiary = Pink80,
    secondary = Colors.BottomNavBarColor,
    background = Colors.BackgroundColor,
    primaryContainer = Colors.InputFieldColor,
    onPrimary = Colors.InputFieldTextColor,
    onSecondaryContainer = Colors.UnselectedTabTint,
    onPrimaryContainer = Colors.OutlineColor,
    onBackground = Colors.ButtonCloseColor,
    onSecondary = Colors.OptionColor,
    onSurface = Color.White,
    onTertiary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    tertiary = Pink40,
    secondary = Colors.BottomNavBarColorLight,
    background = Colors.BackgroundColorLight,
    primaryContainer = Colors.InputFieldColorLight,
    onPrimary = Colors.InputFieldTextColorLight,
    onSecondaryContainer = Colors.UnselectedTabTintLight,
    onPrimaryContainer = Colors.OutlineColorLight,
    onBackground = Colors.ButtonCloseColorLight,
    onSecondary = Colors.OptionColorLight,
    onSurface = Color.Black,
    onTertiary = Colors.InputFieldColor,
)

@Composable
fun RusseLiteratureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

@Composable
fun RusseLiteratureThemeInfinite(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

object Theme {

    val size: Size
        @Composable
        @ReadOnlyComposable
        get() = Size()

}