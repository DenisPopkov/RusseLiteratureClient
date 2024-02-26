package ru.popkov.composemvi.features.splash.nav

import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

object SplashDestination : Destination,
    DestinationDefinition(
        route = "splash",
    )