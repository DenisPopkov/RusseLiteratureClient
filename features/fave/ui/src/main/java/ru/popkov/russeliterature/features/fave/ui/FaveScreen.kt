package ru.popkov.russeliterature.features.fave.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.components.core.card.Card
import ru.popkov.android.core.feature.components.core.card.CardType
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularMedium20
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.Grotesk36

@Composable
internal fun FaveScreen(
    snackbarHostState: SnackbarHostState,
    faveViewModel: FaveViewModel = hiltViewModel(),
    userDataStore: User? = null,
    onGoMainScreen: () -> Unit = {},
) {

    val state by faveViewModel.state.collectAsState()
    val userId = userDataStore?.userId

    LaunchedEffect(Unit) {
        userId?.collectLatest {
            faveViewModel.getFave(userId = it.id)
        }
        faveViewModel.effects
            .collect { effect ->
                when (effect) {
                    is FaveViewEffect.GoToMainScreen -> onGoMainScreen.invoke()
                    is FaveViewEffect.ShowError -> snackbarHostState.showSnackbar(effect.errorMessage)
                }
            }
    }

    Fave(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor),
        state = state,
        onFaveClick = faveViewModel::onAction,
    )
}

@Composable
internal fun Fave(
    modifier: Modifier = Modifier,
    state: FaveState,
    onFaveClick: (FaveViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        if (state.isEmptyState) {
            EmptyState(
                onAction = onFaveClick,
            )
        } else {
            Content(
                state = state,
            )
        }
    }
}

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    state: FaveState,
    onAction: (FaveViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor)
            .statusBarsPadding()
            .padding(vertical = 30.dp)
            .padding(horizontal = 16.dp),
    ) {
        Text(text = stringResource(id = R.string.fave_title), style = Grotesk36)

        if (!state.authors.isNullOrEmpty()) {
            Section(
                modifier = Modifier
                    .padding(top = 30.dp),
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_author
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.authors) { author ->
                    Card(
                        cardId = author.id,
                        cardImageUrl = author.image,
                        cardText = author.name,
                        cardType = CardType.SMALL,
                        isFave = author.isFave,
                        onAction = {
                            onAction.invoke(
                                FaveViewAction.OnAuthorFaveClick(
                                    userId = state.userId,
                                    authorId = author.id,
                                    isFave = !author.isFave,
                                )
                            )
                        },
                    )
                }
            }
        }

        if (!state.articles.isNullOrEmpty()) {
            Section(
                modifier = Modifier
                    .padding(top = 36.dp),
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_articles
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.articles) { article ->
                    Card(
                        cardId = article.id,
                        cardImageUrl = article.image,
                        cardText = article.name,
                        cardType = CardType.LARGE,
                        isFave = article.isFave,
                        onAction = {
                            onAction.invoke(
                                FaveViewAction.OnArticleFaveClick(
                                    userId = state.userId,
                                    articleId = article.id,
                                    isFave = !article.isFave,
                                )
                            )
                        },
                    )
                }
            }
        }

        if (!state.poets.isNullOrEmpty()) {
            Section(
                modifier = Modifier
                    .padding(top = 36.dp),
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_poem
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.poets) { poet ->
                    Card(
                        cardId = poet.id,
                        cardImageUrl = poet.image,
                        cardText = poet.name,
                        cardType = CardType.MEDIUM,
                        isFave = poet.isFave,
                        onAction = {
                            onAction.invoke(
                                FaveViewAction.OnPoetFaveClick(
                                    userId = state.userId,
                                    poetId = poet.id,
                                    isFave = !poet.isFave,
                                )
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
internal fun EmptyState(
    onAction: (FaveViewAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 50.dp),
            text = stringResource(id = R.string.empty_fave_title),
            style = FormularMedium20,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_fave_description),
            style = FormularRegular12,
            color = Colors.GrayTextColor,
        )
        OutlinedButton(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 80.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 24.dp),
            border = BorderStroke(width = 1.dp, color = Colors.OutlineColor),
            onClick = { onAction(FaveViewAction.OnMainScreenClick) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.empty_fave_button),
                style = FormularMedium14,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FaveScreenPreview() {
    Fave(
        state = FaveState(),
    )
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    Content(
        state = FaveState(),
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyStateFaveScreenPreview() {
    EmptyState()
}
