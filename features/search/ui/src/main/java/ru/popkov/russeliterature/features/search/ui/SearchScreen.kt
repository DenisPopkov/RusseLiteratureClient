package ru.popkov.russeliterature.features.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.android.core.feature.components.core.Card
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.components.core.SectionFilter
import ru.popkov.android.core.feature.components.core.models.CardType
import ru.popkov.android.core.feature.components.core.models.SectionFilterItem
import ru.popkov.android.core.feature.components.field.SearchField
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors

@Composable
internal fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val state by searchViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        searchViewModel.effects
            .collect { effect ->
                when (effect) {

                    else -> {}
                }
            }
    }

    Search(
        state = state,
    )
}

@Composable
internal fun Search(
    modifier: Modifier = Modifier,
    state: SearchState,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor)
            .verticalScroll(scrollState)
            .statusBarsPadding()
            .padding(vertical = 30.dp)
            .padding(horizontal = 16.dp),
    ) {

        val filterList = remember {
            mutableListOf(
                SectionFilterItem(sectionName = "все", isSectionSelected = true),
                SectionFilterItem(sectionName = "писатели"),
                SectionFilterItem(sectionName = "статьи"),
                SectionFilterItem(sectionName = "поэты"),
            )
        }

        val authors = remember {
            listOf(
                "Фёдор\nДостоевский",
                "Лев Толстой",
                "Антон Чехов",
                "Всеволод\nКрестовский",
            )
        }

        val articles = remember {
            listOf(
                "Как Толстой Войну и\nмир писал",
                "Реализм в\nлитературе",
            )
        }

        val poems = remember {
            listOf(
                "Мир Анны\nАхматовой",
                "Ночь, улица,\nфонарь, аптека...",
            )
        }

        SectionFilter(sectionFilterListener = filterList)
        SearchField(
            modifier = Modifier.padding(top = 40.dp),
            onChange = {

            }
        )
        Section(
            modifier = Modifier
                .padding(top = 30.dp),
            sectionText = R.string.section_author
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(authors) {
                Card(cardImageUrl = "", cardText = it, cardType = CardType.SMALL)
            }
        }

        Section(
            modifier = Modifier
                .padding(top = 36.dp),
            sectionText = R.string.section_articles
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(articles) {
                Card(cardImageUrl = "", cardText = it, cardType = CardType.LARGE)
            }
        }

        Section(
            modifier = Modifier
                .padding(top = 36.dp),
            sectionText = R.string.section_poem
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(poems) {
                Card(cardImageUrl = "", cardText = it, cardType = CardType.MEDIUM)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    Search(
        state = SearchState(),
    )
}
