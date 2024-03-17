package ru.popkov.russeliterature.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import ru.popkov.android.core.feature.components.core.Card
import ru.popkov.android.core.feature.components.core.CardType
import ru.popkov.android.core.feature.components.core.Section
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.GothicBold44
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 390.dp)
        ) {
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components { add(ImageDecoderDecoder.Factory()) }.build()
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.ic_chipi)
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(top = 110.dp),
                    text = stringResource(id = ru.popkov.russeliterature.features.home.ui.R.string.header_title),
                    style = GothicBold44
                )
                Text(
                    modifier = Modifier.padding(top = 22.dp),
                    text = stringResource(id = ru.popkov.russeliterature.features.home.ui.R.string.header_desc),
                    style = FormularRegular14
                )
            }
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
