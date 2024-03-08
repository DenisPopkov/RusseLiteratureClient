package ru.popkov.russeliterature

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.popkov.android.core.feature.nav.belongsTo
import ru.popkov.android.core.feature.ui.NavProvider
import ru.popkov.russeliterature.theme.Colors

@Composable
fun MainNavBar(
    items: List<NavProvider.BottomBarItem>,
    navController: NavController,
) {
    NavigationBar(
        containerColor = Colors.BackgroundColor,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                alwaysShowLabel = false,
                interactionSource = MutableInteractionSource(),
                colors = NavigationBarItemColors(
                    selectedIconColor = Colors.SelectedTabTint,
                    unselectedIconColor = Colors.UnselectedTabTint,
                    selectedIndicatorColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    unselectedTextColor = Color.Unspecified,
                    disabledIconColor = Color.Unspecified,
                    disabledTextColor = Color.Unspecified
                ),
                selected = currentDestination?.belongsTo(item.route) ?: false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}