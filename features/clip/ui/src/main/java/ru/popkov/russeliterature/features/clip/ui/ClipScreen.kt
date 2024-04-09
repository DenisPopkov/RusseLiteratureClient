package ru.popkov.russeliterature.features.clip.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.popkov.android.core.feature.ui.UiModePreviews
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularRegular14
import ru.popkov.russeliterature.theme.GothicBold36
import ru.popkov.russeliterature.theme.Grotesk36
import ru.popkov.russeliterature.theme.RusseLiteratureTheme

@Composable
fun ClipScreen(
    snackbarHostState: SnackbarHostState,
    clipViewModel: ClipViewModel = hiltViewModel(),

    onToQuizClick: (quizId: Long) -> Unit,
) {
    val state by clipViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        clipViewModel.getClip()
        clipViewModel.effects
            .collect { effect ->
                when (effect) {
                    is ClipViewEffect.OnToQuizEffect -> onToQuizClick(effect.quizId)
                    is ClipViewEffect.ShowError -> snackbarHostState.showSnackbar(effect.errorMessage)
                }
            }
    }

    Clip(
        state = state,
        onToQuizClick = clipViewModel::onAction,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Clip(
    modifier: Modifier = Modifier,
    state: ClipState,
    onToQuizClick: (ClipViewAction) -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = {
        state.clip.text.size
    })

    Box {
        VerticalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxSize(),
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(height = 390.dp)
                        .alpha(alpha = 0.4f),
                    model = state.clip.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 220.dp),
                        text = state.clip.text[page].title,
                        style = GothicBold36,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        text = state.clip.text[page].text,
                        style = FormularRegular14,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    AnimatedVisibility(visible = pagerState.currentPage == state.clip.text.size - 1) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            shape = RoundedCornerShape(size = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            onClick = { onToQuizClick(ClipViewAction.OnToQuizClick(state.clip.clipId)) }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                text = stringResource(id = R.string.clip_quiz),
                                style = FormularMedium14,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 52.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.clip_title),
                style = Grotesk36,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@UiModePreviews
@Composable
private fun ClipScreenPreview() {
    RusseLiteratureTheme {
        Clip(
            state = ClipState(),
        )
    }
}
