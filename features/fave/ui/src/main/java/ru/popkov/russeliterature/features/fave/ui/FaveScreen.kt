package ru.popkov.russeliterature.features.fave.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.popkov.russeliterature.theme.Colors
import ru.popkov.russeliterature.theme.FormularMedium14
import ru.popkov.russeliterature.theme.FormularMedium20
import ru.popkov.russeliterature.theme.FormularRegular12

@Composable
internal fun FaveScreen(
    faveViewModel: FaveViewModel = hiltViewModel(),
    onGoMainScreen: () -> Unit = {},
) {

    val state by faveViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        faveViewModel.effects
            .collect { effect ->
                when (effect) {
                    FaveViewEffect.GoToMainScreen -> onGoMainScreen.invoke()
                    FaveViewEffect.ShowEmptyState -> {}
                }
            }
    }

    Fave(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.BackgroundColor),
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
        // Header
        // Content
    }
}

@Composable
internal fun EmptyState(
    onAction: (FaveViewAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 50.dp),
            text = stringResource(id = R.string.empty_fave_title),
            style = FormularMedium20,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 60.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_fave_description),
            style = FormularRegular12,
            color = Colors.GrayTextColor,
        )
        OutlinedButton(
            modifier = Modifier
                .padding(top = 40.dp)
                .padding(horizontal = 80.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 24.dp),
            border = BorderStroke(width = 1.dp, color = Colors.OutlineColor),
            onClick = { onAction(FaveViewAction.OnMainScreenClick) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.empty_fave_button),
                style = FormularMedium14,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FaveScreenPreview() {
    Fave(
        state = FaveState(),
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyStateFaveScreenPreview() {
    EmptyState()
}
