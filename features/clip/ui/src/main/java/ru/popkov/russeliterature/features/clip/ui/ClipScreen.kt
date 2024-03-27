package ru.popkov.russeliterature.features.clip.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.android.core.feature.ui.R
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularRegular12
import ru.popkov.russeliterature.theme.GothicBold44
import ru.popkov.russeliterature.theme.Grotesk36

@Composable
fun ClipScreen(
    snackbarHostState: SnackbarHostState,
    clipViewModel: ClipViewModel = hiltViewModel(),
) {
    val state by clipViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        clipViewModel.effects
            .collect { effect ->
                when (effect) {

                    else -> {}
                }
            }
    }

    Clip(
        state = state,
    )
}

@Immutable
data class ClipItem(
    val id: Int = 0,
    val clipTitle: String,
    val clipDescription: String,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Clip(
    modifier: Modifier = Modifier,
    state: ClipState,
) {
    val pagerState = rememberPagerState(pageCount = {
        state.clipItems.size
    })

    VerticalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize(),
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Colors.BackgroundColor)
        ) {
            Image(
                modifier = Modifier
                    .height(height = 390.dp)
                    .alpha(alpha = 0.5f),
                painter = painterResource(id = R.drawable.ic_article),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .padding(top = 42.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = ru.popkov.russeliterature.features.clip.ui.R.string.clip_title),
                    style = Grotesk36,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_fave),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .padding(all = 16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(top = 200.dp),
                    text = state.clipItems[page].clipTitle,
                    style = GothicBold44
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = state.clipItems[page].clipDescription,
                    style = FormularRegular12
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClipScreenPreview() {
    Clip(
        state = ClipState(),
    )
}
