package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.FormularRegular16

@Composable
fun QuizScreen(
    snackbarHostState: SnackbarHostState,
    quizViewModel: QuizViewModel = hiltViewModel(),
    onCloseClick: () -> Unit
) {
    val state by quizViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        quizViewModel.effects
            .collect { effect ->
                when (effect) {
                    QuizViewEffect.OnCloseEffect -> onCloseClick.invoke()
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
            .background(color = Colors.ButtonCloseColor)
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
    quizAnswer: QuizAnswer,
    onAnswerClick: (QuizViewAction) -> Unit = {},
    icon: ImageVector? = null,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(size = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Colors.QuizButtonColor),
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
                    .background(color = Colors.OptionColor),
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
                        text = ('A'..'Z').toList()[quizAnswer.id].toString(),
                        style = FormularRegular16,
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                text = quizAnswer.answer,
                style = FormularRegular16,
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
            .background(Colors.BackgroundColor)
            .padding(top = 20.dp)
            .padding(all = 16.dp)
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.End),
            onCloseClick = { onAction.invoke(QuizViewAction.OnCloseClick) }
        )
        when (state.quiz) {
            Quiz.QUESTION -> {
                Text(
                    modifier = Modifier
                        .padding(vertical = 100.dp),
                    text = state.item.quizQuestion,
                    textAlign = TextAlign.Center,
                    style = FormularRegular16,
                )
                state.item.answers.forEach { answer ->
                    Question(
                        quizAnswer = answer,
                        onAnswerClick = { onAction.invoke(QuizViewAction.OnAnswerClick) },
                    )
                }
            }

            else -> {
                Result(
                    state = state,
                    onAnswerClick = { onAction.invoke(QuizViewAction.OnAnswerClick) },
                )
            }
        }
    }
}

@Composable
internal fun ColumnScope.Result(
    modifier: Modifier = Modifier,
    state: QuizState,
    onAnswerClick: (QuizViewAction) -> Unit = {},
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .height(height = 240.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp)),
        painter = painterResource(id = ru.popkov.android.core.feature.ui.R.drawable.ic_article),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
    Text(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 6.dp)
            .align(Alignment.Start),
        text = stringResource(id = R.string.quiz_right),
        style = FormularRegular12,
        color = Color.White.copy(alpha = 0.7f),
    )
    Question(
        quizAnswer = state.item.answers.find { it.isCorrect }!!,
        onAnswerClick = onAnswerClick,
        icon = Icons.Filled.Check,
    )
    Text(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 6.dp)
            .align(Alignment.Start),
        text = stringResource(id = R.string.quiz_answer),
        style = FormularRegular12,
        color = Color.White.copy(alpha = 0.7f),
    )
    Question(
        quizAnswer = state.item.answers.find { !it.isCorrect }!!,
        onAnswerClick = onAnswerClick,
        icon = Icons.Filled.Close,
    )
    Text(
        modifier = Modifier
            .padding(vertical = 16.dp),
        text = state.item.quizAnswer,
        style = FormularRegular14,
    )
}

@Preview(showBackground = true)
@Composable
private fun QuizScreenPreview() {
    Quiz(
        state = QuizState(),
    )
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    Quiz(
        state = QuizState(quiz = Quiz.RESULTS),
    )
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    Header()
}

@Preview(showBackground = true)
@Composable
private fun AnswerPreview() {
    Question(
        quizAnswer = QuizAnswer(
            id = 0,
            answer = "Признались в краже соли",
            isCorrect = false,
        )
    )
}