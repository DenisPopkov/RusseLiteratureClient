package ru.popkov.russeliterature.features.search.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import ru.popkov.android.core.feature.components.core.SectionFilter
import ru.popkov.android.core.feature.components.core.card.Card
import ru.popkov.android.core.feature.components.core.card.CardType
import ru.popkov.android.core.feature.components.field.SearchField
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularMedium20
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
internal fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onToMainClick: () -> Unit = {},
    onCardClick: (cardId: Long) -> Unit = {},
    onSectionClick: (sectionId: Int) -> Unit = {},
) {
    val state by searchViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        searchViewModel.getAll()
        searchViewModel.effects
            .collect { effect ->
                when (effect) {
                    is SearchViewEffect.ShowError -> snackbarHostState.showSnackbar(effect.errorMessage)
                    is SearchViewEffect.GoToMainScreen -> onToMainClick.invoke()
                    is SearchViewEffect.OnSectionClick -> onSectionClick.invoke(effect.sectionId)
                    is SearchViewEffect.OnCardClick -> onCardClick.invoke(effect.cardId)
                }
            }
    }

    Search(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        state = state,
        onFaveClick = searchViewModel::onAction,
        onAction = searchViewModel::onAction,
    )
}

@Composable
internal fun Search(
    modifier: Modifier = Modifier,
    state: SearchState,
    onFaveClick: (SearchViewAction) -> Unit = {},
    onAction: (SearchViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(vertical = 30.dp)
            .padding(horizontal = 16.dp),
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(space = 18.dp),
        ) {
            items(state.filterList) {
                SectionFilter(sectionItem = it, onSectionFilterClick = {
                    onAction(SearchViewAction.OnSectionItemClick(it.sectionType))
                })
            }
        }
        SearchField(
            modifier = Modifier.padding(top = 40.dp),
            onChange = {
                onAction(SearchViewAction.OnSearchChange(it))
            }
        )

        if (state.isEmptyState) {
            Spacer(modifier = Modifier.weight(1f))
            EmptyState(
                onAction = onFaveClick,
            )
            Spacer(modifier = Modifier.weight(1f))
        } else {
            Content(
                modifier = modifier,
                state = state,
                onAction = onAction,
            )
        }
    }
}

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    state: SearchState,
    onAction: (SearchViewAction) -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        if (!state.authors.isNullOrEmpty()) {
            Section(
                modifier = Modifier
                    .padding(top = 30.dp),
                sectionText = ru.popkov.android.core.feature.ui.R.string.section_author,
                onSectionClick = {
                    onAction(SearchViewAction.OnSectionClick(0))
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
                                SearchViewAction.OnCardClick(author.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                SearchViewAction.OnAuthorFaveClick(
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
                    onAction(SearchViewAction.OnSectionClick(1))
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
                                SearchViewAction.OnCardClick(article.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                SearchViewAction.OnArticleFaveClick(
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
                    onAction(SearchViewAction.OnSectionClick(2))
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
                                SearchViewAction.OnCardClick(poet.id)
                            )
                        },
                        onFaveActionClick = {
                            onAction.invoke(
                                SearchViewAction.OnPoetFaveClick(
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
    onAction: (SearchViewAction) -> Unit = {},
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
            text = stringResource(id = R.string.empty_search_title),
            style = FormularMedium20,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_search_description),
            style = FormularRegular12,
            color = MaterialTheme.colorScheme.onSurface,
        )
        OutlinedButton(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 80.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 24.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onPrimaryContainer),
            onClick = { onAction(SearchViewAction.OnMainScreenClick) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.empty_search_button),
                style = FormularMedium14,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun SearchScreenPreview() {
    RusseLiteratureTheme {
        Search(
            state = SearchState(),
        )
    }
}
