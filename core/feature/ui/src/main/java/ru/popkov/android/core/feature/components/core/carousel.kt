package ru.popkov.android.core.feature.components.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.GothicBold44

@Immutable
data class CarouselItem(
    val id: Int = 0,
    val articleTitle: String,
    val articleDescription: String,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    carouselItems: List<CarouselItem>,
) {
    val pagerState = rememberPagerState(pageCount = {
        carouselItems.size
    })

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .height(height = 390.dp),
    ) { page ->
        Box {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_article),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(all = 16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(top = 110.dp),
                    text = carouselItems[page].articleTitle,
                    style = GothicBold44
                )
                Text(
                    modifier = Modifier.padding(top = 22.dp),
                    text = carouselItems[page].articleDescription,
                    style = FormularRegular14
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    repeat(times = pagerState.pageCount) { iteration ->
                        Canvas(
                            modifier = Modifier
                                .size(10.dp)
                        ) {
                            val isSelected = pagerState.currentPage == iteration
                            drawCircle(
                                radius = if (isSelected) 8f else 6f,
                                color = if (isSelected) Color.White else Colors.DotGrayColor,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarouselPreview() {
    Carousel(
        carouselItems = listOf(
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
    )
}
