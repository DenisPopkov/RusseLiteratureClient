package ru.popkov.russeliterature.features.fave.nav

import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

object FaveDestination : Destination,
    DestinationDefinition(
        route = "fave",
    )