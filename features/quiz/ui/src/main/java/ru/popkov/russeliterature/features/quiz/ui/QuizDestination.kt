package ru.popkov.russeliterature.features.quiz.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.popkov.android.core.feature.nav.Destination
import ru.popkov.android.core.feature.nav.DestinationDefinition

const val quizRoute = "quiz"
const val quizArg = "quizArg"

data class QuizDestination(
    val quizId: Long,
) : Destination {

    companion object : DestinationDefinition(
        route = "$quizRoute?$quizArg={$quizArg}",
        args = listOf(
            navArgument(quizArg) {
                type = NavType.LongType
            },
        ),
    )

    data class Args(
        val quizId: Long?,
    ) {

        constructor(savedStateHandle: SavedStateHandle) : this(
            quizId = savedStateHandle.get<Long>(quizArg)
        )
    }

    override fun toString() =
        "$quizRoute?$quizArg=$quizId"
}