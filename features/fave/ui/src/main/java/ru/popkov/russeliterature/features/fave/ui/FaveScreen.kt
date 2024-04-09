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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.components.core.card.Card
import ru.popkov.android.core.feature.components.core.card.CardType
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularMedium20
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.Grotesk36
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
internal fun FaveScreen(
    snackbarHostState: SnackbarHostState,
    faveViewModel: FaveViewModel = hiltViewModel(),
    onGoMainScreen: () -> Unit = {},
    onCardClick: (cardId: Long) -> Unit = {},
    onSectionClick: (sectionId: Int) -> Unit = {},
) {

    val state by faveViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        faveViewModel.getFave()
        faveViewModel.effects
            .collect { effect ->
                when (effect) {
                    is FaveViewEffect.OnCardClick -> onCardClick.invoke(effect.cardId)
                    is FaveViewEffect.GoToMainScreen -> onGoMainScreen.invoke()
                    is FaveViewEffect.ShowError -> snackbarHostState.showSnackbar(effect.errorMessage)
                    is FaveViewEffect.OnSectionClick -> onSectionClick.invoke(effect.sectionId)
                }
            }
    }

    Fave(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
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
                onAction = onFaveClick,
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
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(vertical = 30.dp)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.fave_title),
            style = Grotesk36,
            color = MaterialTheme.colorScheme.onSurface,
        )

        if (!state.authors.isNullOrEmpty()) {
            Section(
                modifier = Modifier
                    .padding(top = 30.dp),
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_author,
                onSectionClick = {
                    onAction(FaveViewAction.OnSectionClick(0))
                }
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.authors) { author ->
                    Card(
                        cardImageUrl = author.image,
                        cardText = author.name,
                        cardType = CardType.SMALL,
                        isFave = author.isFave,
                        onCardActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnCardClick(author.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnAuthorFaveClick(
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
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_articles,
                onSectionClick = {
                    onAction(FaveViewAction.OnSectionClick(1))
                }
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.articles) { article ->
                    Card(
                        cardImageUrl = article.image,
                        cardText = article.name,
                        cardType = CardType.LARGE,
                        isFave = article.isFave,
                        onCardActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnCardClick(article.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnArticleFaveClick(
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
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_poem,
                onSectionClick = {
                    onAction(FaveViewAction.OnSectionClick(2))
                }
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(state.poets) { poet ->
                    Card(
                        cardImageUrl = poet.image,
                        cardText = poet.name,
                        cardType = CardType.MEDIUM,
                        isFave = poet.isFave,
                        onCardActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnCardClick(poet.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                FaveViewAction.OnPoetFaveClick(
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
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 50.dp),
            text = stringResource(id = R.string.empty_fave_title),
            style = FormularMedium20,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_fave_description),
            style = FormularRegular12,
            color = MaterialTheme.colorScheme.onSurface,
        )
        OutlinedButton(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 80.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 24.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            onClick = { onAction(FaveViewAction.OnMainScreenClick) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.empty_fave_button),
                style = FormularMedium14,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun FaveScreenPreview() {
    RusseLiteratureTheme {
        Fave(
            state = FaveState(),
        )
    }
}

@UiModePreviews
@Composable
private fun ContentPreview() {
    RusseLiteratureTheme {
        Content(
            state = FaveState(),
        )
    }
}

@UiModePreviews
@Composable
private fun EmptyStateFaveScreenPreview() {
    RusseLiteratureTheme {
        EmptyState()
    }
}
