package ru.popkov.russeliterature.features.section.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val sectionRoute = "section"
const val sectionArg = "sectionArg"

data class SectionDestination(
    val sectionType: Int,
) : Destination {

    companion object : DestinationDefinition(
        route = "$sectionRoute?$sectionArg={$sectionArg}",
        args = listOf(
            navArgument(sectionArg) {
                type = NavType.IntType
            },
        ),
    )

    data class Args(
        val sectionType: Int?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            sectionType = savedStateHandle.get<Int>(sectionArg)
        )
    }

    override fun toString() =
        "$sectionRoute?$sectionArg=$sectionType"
}
