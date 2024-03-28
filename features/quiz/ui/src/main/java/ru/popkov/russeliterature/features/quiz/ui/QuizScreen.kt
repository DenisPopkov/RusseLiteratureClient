package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    snackbarHostState: SnackbarHostState,
    quizViewModel: QuizViewModel = hiltViewModel(),
) {
    val state by quizViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        quizViewModel.effects
            .collect { effect ->
                when (effect) {
                    else -> {}
                }
            }
    }

    Quiz(
        state = state,
        onAnswerClick = quizViewModel::onAction
    )
}

data class QuizItem(
    val id: Int = 0,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Quiz(
    modifier: Modifier = Modifier,
    state: QuizState,
    onAnswerClick: (QuizViewAction) -> Unit = {},
) {
    
}

@Preview(showBackground = true)
@Composable
private fun QuizScreenPreview() {
    Quiz(
        state = QuizState(),
    )
}
