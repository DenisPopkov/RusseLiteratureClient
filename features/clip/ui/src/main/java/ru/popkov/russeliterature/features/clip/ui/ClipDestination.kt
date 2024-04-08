package ru.popkov.russeliterature.features.clip.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val clipRoute = "clip"
const val clipArg = "clipArg"

data class ClipDestination(
    val clipId: Long,
) : Destination {

    companion object : DestinationDefinition(
        route = "$clipRoute?$clipArg={$clipArg}",
        args = listOf(
            navArgument(clipArg) {
                type = NavType.LongType
            },
        ),
    )

    data class Args(
        val clipId: Long?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            clipId = savedStateHandle.get<Long>(clipArg)
        )
    }

    override fun toString() =
        "$clipRoute?$clipArg=$clipId"
}
