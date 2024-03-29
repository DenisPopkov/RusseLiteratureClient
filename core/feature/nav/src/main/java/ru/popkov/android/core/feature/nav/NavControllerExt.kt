package ru.popkov.android.core.feature.nav

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigate(destination: Destination, builder: NavOptionsBuilder.() -> Unit = {}) =
    navigate(destination.toString(), builder)