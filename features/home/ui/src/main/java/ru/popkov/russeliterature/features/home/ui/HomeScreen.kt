package ru.popkov.russeliterature.features.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.android.core.feature.components.core.Carousel
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.components.core.card.Card
import ru.popkov.android.core.feature.components.core.card.CardType
import ru.popkov.android.core.feature.components.core.models.Carousel
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors

@Composable
internal fun HomeScreen(
    snackbarHostState: SnackbarHostState,

    homeViewModel: HomeViewModel = hiltViewModel(),
    onCardClick: (cardId: Long) -> Unit = {},
    onSectionClick: (sectionId: Int) -> Unit = {},
) {
    val state by homeViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getFeed()
        homeViewModel.effects
            .collect { effect ->
                when (effect) {
                    is HomeViewEffect.ShowError ->
                        snackbarHostState.showSnackbar(effect.errorMessage)

                    is HomeViewEffect.OnCardClick -> onCardClick.invoke(effect.cardId)

                    is HomeViewEffect.OnSectionClick -> onSectionClick.invoke(effect.sectionId)
                }
            }
    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Home(
            state = state,
            onAction = homeViewModel::onAction,
        )

        AnimatedVisibility(visible = state.isLoading) {
            CircularProgressIndicator(color = Color.LightGray)
        }
    }
}

@Composable
private fun Home(
    state: HomeState,
    onAction: (HomeViewAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp),
    ) {

        state.articles?.map {
            Carousel(
                id = it.id,
                articleTitle = it.name,
                articleDescription = it.description,
                articleImage = it.image,
            )
        }?.let {
            Carousel(
                modifier = Modifier.clickable { },
                carouselItems = it
            )
        }

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp),
            sectionText = R.string.section_author,
            onSectionClick = {
                onAction(HomeViewAction.OnSectionClick(0))
            }
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.authors ?: emptyList()) { author ->
                Card(
                    cardImageUrl = author.image,
                    cardText = author.name,
                    cardType = CardType.SMALL,
                    isFave = author.isFave,
                    onCardActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnCardClick(author.id)
                        )
                    },
                    onFaveActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnAuthorFaveClick(
                                authorId = author.id,
                                isFave = !author.isFave,
                            )
                        )
                    },
                )
            }
        }

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp),
            sectionText = R.string.section_articles,
            onSectionClick = {
                onAction(HomeViewAction.OnSectionClick(1))
            }
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.articles ?: emptyList()) { article ->
                Card(
                    cardImageUrl = article.image,
                    cardText = article.name,
                    cardType = CardType.LARGE,
                    isFave = article.isFave,
                    onCardActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnCardClick(article.id)
                        )
                    },
                    onFaveActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnArticleFaveClick(
                                articleId = article.id,
                                isFave = !article.isFave,
                            )
                        )
                    },
                )
            }
        }

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp),
            sectionText = R.string.section_poem,
            onSectionClick = {
                onAction(HomeViewAction.OnSectionClick(2))
            }
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.poets ?: emptyList()) { poet ->
                Card(
                    cardImageUrl = poet.image,
                    cardText = poet.name,
                    cardType = CardType.MEDIUM,
                    isFave = poet.isFave,
                    onCardActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnCardClick(poet.id)
                        )
                    },
                    onFaveActionClick = {
                        onAction.invoke(
                            HomeViewAction.OnPoetFaveClick(
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
