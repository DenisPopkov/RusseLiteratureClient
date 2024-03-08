package ru.popkov.russeliterature.features.search.nav

import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

object SearchDestination : Destination,
    DestinationDefinition(
        route = "search",
    )