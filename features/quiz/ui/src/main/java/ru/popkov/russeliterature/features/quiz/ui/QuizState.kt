package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.runtime.Immutable
import ru.popkov.russeliterature.features.auth.domain.model.Quiz

enum class QuizScreenState {
    QUESTION,
    RESULTS,
    ;
}

@Immutable
internal data class QuizState(

    val quizState: QuizScreenState = QuizScreenState.QUESTION,
    val quiz: Quiz? = null,
    val isLoading: Boolean = false,
)
