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
import kotlinx.coroutines.flow.collectLatest
import ru.popkov.android.core.feature.components.core.Card
import ru.popkov.android.core.feature.components.core.Carousel
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.components.core.models.CardType
import ru.popkov.android.core.feature.components.core.models.Carousel
import ru.popkov.android.core.feature.ui.R
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.theme.Colors

@Composable
internal fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    userDataStore: User? = null,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onCardClick: () -> Unit = {},
) {

    val state by homeViewModel.state.collectAsState()
    val userId = userDataStore?.userId

    LaunchedEffect(Unit) {
        userId?.collectLatest {
            homeViewModel.getFeed(userId = it.id)
        }
        homeViewModel.effects
            .collect { effect ->
                when (effect) {
                    is HomeViewEffect.ShowError ->
                        snackbarHostState.showSnackbar(effect.errorMessage)
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

        state.feed?.articles?.map {
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
            sectionText = R.string.section_author
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.feed?.authors ?: emptyList()) {
                Card(cardImageUrl = it.image, cardText = it.name, cardType = CardType.SMALL)
            }
        }

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp),
            sectionText = R.string.section_articles
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.feed?.articles ?: emptyList()) {
                Card(cardImageUrl = it.image, cardText = it.name, cardType = CardType.LARGE)
            }
        }

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp),
            sectionText = R.string.section_poem
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(state.feed?.poets ?: emptyList()) {
                Card(cardImageUrl = it.image, cardText = it.name, cardType = CardType.MEDIUM)
            }
        }
    }
}
