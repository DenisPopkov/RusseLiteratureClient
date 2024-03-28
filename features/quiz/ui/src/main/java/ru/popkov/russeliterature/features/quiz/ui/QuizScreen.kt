package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
                    QuizViewEffect.OnCloseEffect -> onCloseClick()
                }
            }
    }

    Quiz(
        state = state,
        onAnswerClick = quizViewModel::onAction
    )
}

@Immutable
data class QuizAnswer(
    val id: Int = 0,
    val answer: String = "",
    val isCorrect: Boolean = false,
)

@Immutable
data class QuizItem(
    val id: Int = 0,
    val quizQuestion: String = "В давние времена Нижний был центром соляной промышленности. В 1864 году соляные склады оказались пустыми.",
    val quizAnswer: String = "В то время действовала монополия на продажу соли, а нечистые на руку купцы потихоньку распродавали её и списывали убытки на паводки. Склады стояли у реки и, действительно, могли подновляться. Афера удачно складывалась до того, как Александр II отменил монополию и приказал срочно продать соль. Паводка в том году не было, так что свалить убытки было не на что. Участники аферы осудили по всей строгости закона. По городской легенде, причастному к скандалу купцу.",
    val answers: List<QuizAnswer> = listOf(
        QuizAnswer(
            id = 0,
            answer = "Признались в краже соли",
            isCorrect = true,
        ),
        QuizAnswer(
            id = 1,
            answer = "Паводок уничтожил соль",
            isCorrect = false,
        ),
        QuizAnswer(
            id = 2,
            answer = "Отгрузили соль в долг до паводка",
            isCorrect = false,
        ),
    ),
)

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onCloseClick: (QuizViewAction) -> Unit = {},
) {
    IconButton(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .background(color = Colors.ButtonCloseColor),
        onClick = { onCloseClick(QuizViewAction.OnCloseClick) },
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            tint = Color.White,
            contentDescription = null,
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
                    .padding(all = 10.dp),
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
    onAnswerClick: (QuizViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Colors.BackgroundColor)
            .padding(all = 16.dp)
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.End)
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
                        onAnswerClick = onAnswerClick,
                    )
                }
            }

            else -> {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp)
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
                    text = "ПРАВИЛЬНЫЙ ОТВЕТ:",
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
                        .padding(top = 20.dp, bottom = 6.dp)
                        .align(Alignment.Start),
                    text = "ВАШ ОТВЕТ:",
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
        }
    }
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