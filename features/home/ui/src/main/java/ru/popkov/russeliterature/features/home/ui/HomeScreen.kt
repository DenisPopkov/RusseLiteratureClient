package ru.popkov.russeliterature.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.popkov.android.core.feature.components.core.Card
import ru.popkov.android.core.feature.components.core.CardType
import ru.popkov.android.core.feature.components.core.Carousel
import ru.popkov.android.core.feature.components.core.CarouselItem
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.Theme

@Composable
internal fun HomeScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Colors.BackgroundColor),
    ) {
        val headerArticles = remember {
            listOf(
                CarouselItem(
                    id = 0,
                    articleTitle = "реализм в\nлитературе",
                    articleDescription = "В СТАТЬЕ РАССКАЗЫВАЕМ ПРО АВТОРОВ И\nКЛЮЧЕВЫЕ ПРОИЗВЕДЕНИЯ В ЭТОМ\nНАПРАВЛЕНИИ",
                ),
                CarouselItem(
                    id = 1,
                    articleTitle = "реализм в\nлитературе (2)",
                    articleDescription = "В СТАТЬЕ РАССКАЗЫВАЕМ ПРО АВТОРОВ И\nКЛЮЧЕВЫЕ ПРОИЗВЕДЕНИЯ В ЭТОМ\nНАПРАВЛЕНИИ",
                ),
                CarouselItem(
                    id = 2,
                    articleTitle = "реализм в\nлитературе (3)",
                    articleDescription = "В СТАТЬЕ РАССКАЗЫВАЕМ ПРО АВТОРОВ И\nКЛЮЧЕВЫЕ ПРОИЗВЕДЕНИЯ В ЭТОМ\nНАПРАВЛЕНИИ",
                ),
                CarouselItem(
                    id = 3,
                    articleTitle = "реализм в\nлитературе (4)",
                    articleDescription = "В СТАТЬЕ РАССКАЗЫВАЕМ ПРО АВТОРОВ И\nКЛЮЧЕВЫЕ ПРОИЗВЕДЕНИЯ В ЭТОМ\nНАПРАВЛЕНИИ",
                ),
                CarouselItem(
                    id = 4,
                    articleTitle = "реализм в\nлитературе (5)",
                    articleDescription = "В СТАТЬЕ РАССКАЗЫВАЕМ ПРО АВТОРОВ И\nКЛЮЧЕВЫЕ ПРОИЗВЕДЕНИЯ В ЭТОМ\nНАПРАВЛЕНИИ",
                ),
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

        Carousel(
            carouselItems = headerArticles
        )

        Section(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp),
            sectionText = R.string.section_author
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(authors) {
                Card(cardImageUrl = "", cardText = it, cardType = CardType.SMALL)
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
                .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(articles) {
                Card(cardImageUrl = "", cardText = it, cardType = CardType.LARGE)
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
                .padding(horizontal = 6.dp),
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
private fun Preview() {
    Theme {
        HomeScreen()
    }
}
