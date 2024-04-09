package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.features.auth.domain.model.Answer
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.FormularRegular16
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
fun QuizScreen(
    snackbarHostState: SnackbarHostState,
    quizViewModel: QuizViewModel = hiltViewModel(),
    onCloseClick: () -> Unit
) {
    val state by quizViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        quizViewModel.getQuiz()
        quizViewModel.effects
            .collect { effect ->
                when (effect) {
                    is QuizViewEffect.OnCloseEffect -> onCloseClick.invoke()
                    is QuizViewEffect.ShowError -> snackbarHostState.showSnackbar(effect.errorMessage)
                }
            }
    }

    Quiz(
        state = state,
        onAction = quizViewModel::onAction,
    )
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(30.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = MaterialTheme.colorScheme.onBackground)
            .clickable { onCloseClick.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            tint = Color.White,
        )
    }
}

@Composable
fun Question(
    quizAnswer: Answer,
    onAnswerClick: (QuizViewAction) -> Unit = {},
    index: Int,
    icon: ImageVector? = null,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(size = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = { onAnswerClick(QuizViewAction.OnAnswerClick) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(size = 26.dp)
                    .clip(shape = CircleShape)
                    .background(color = MaterialTheme.colorScheme.onSecondary),
                contentAlignment = Alignment.Center,
            ) {
                if (icon != null) {
                    Icon(
                        modifier = Modifier
                            .size(size = 18.dp),
                        imageVector = icon,
                        tint = Color.White,
                        contentDescription = null,
                    )
                } else {
                    Text(
                        text = ('A'..'Z').toList()[index].toString(),
                        style = FormularRegular16,
                        color = Color.White,
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                text = quizAnswer.text,
                style = FormularRegular16,
                color = MaterialTheme.colorScheme.onTertiary,
            )
        }
    }
}

@Composable
internal fun Quiz(
    modifier: Modifier = Modifier,
    state: QuizState,
    onAction: (QuizViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(top = 20.dp)
            .padding(all = 16.dp),
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.End),
            onCloseClick = { onAction.invoke(QuizViewAction.OnCloseClick) }
        )
        when (state.quizState) {
            QuizScreenState.QUESTION -> {
                Text(
                    modifier = Modifier
                        .padding(vertical = 100.dp),
                    text = state.quiz?.question ?: "",
                    textAlign = TextAlign.Center,
                    style = FormularRegular16,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                state.quiz?.answers?.forEachIndexed { index, answer ->
                    Question(
                        quizAnswer = answer,
                        onAnswerClick = { onAction.invoke(QuizViewAction.OnAnswerClick) },
                        index = index,
                    )
                }
            }

            else -> {
                Result(
                    state = state,
                )
            }
        }
    }
}

@Composable
internal fun ColumnScope.Result(
    modifier: Modifier = Modifier,
    state: QuizState,
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .height(height = 240.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp)),
        model = state.quiz?.image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
    Text(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 6.dp)
            .align(Alignment.Start),
        text = stringResource(id = R.string.quiz_right),
        style = FormularRegular12,
        color = MaterialTheme.colorScheme.onSurface,
    )
    state.quiz?.answers?.find { it.isRight }?.let {
        Question(
            quizAnswer = it,
            icon = Icons.Filled.Check,
            index = 0,
        )
    }
    Text(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 6.dp)
            .align(Alignment.Start),
        text = stringResource(id = R.string.quiz_answer),
        style = FormularRegular12,
        color = MaterialTheme.colorScheme.onSurface,
    )
    state.quiz?.answers?.find { !it.isRight }?.let {
        Question(
            quizAnswer = it,
            icon = Icons.Filled.Close,
            index = 1,
        )
    }
    Text(
        modifier = Modifier
            .padding(vertical = 16.dp),
        text = state.quiz?.description ?: "",
        style = FormularRegular14,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@UiModePreviews
@Composable
private fun QuizScreenPreview() {
    RusseLiteratureTheme {
        Quiz(
            state = QuizState(),
        )
    }
}

@UiModePreviews
@Composable
private fun ResultScreenPreview() {
    RusseLiteratureTheme {
        Quiz(
            state = QuizState(quizState = QuizScreenState.RESULTS),
        )
    }
}

@UiModePreviews
@Composable
private fun HeaderPreview() {
    RusseLiteratureTheme {
        Header()
    }
}

@UiModePreviews
@Composable
private fun AnswerPreview() {
    RusseLiteratureTheme {
        Question(
            quizAnswer = Answer(
                answerId = 0,
                text = "Признались в краже соли",
                isRight = false,
            ),
            index = 0,
        )
    }
}