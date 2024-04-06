package ru.popkov.russeliterature.features.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.model.Feed
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
) : ViewModel(),
    StateProvider<HomeState> by StateDelegate(HomeState()),
    EffectsProvider<HomeViewEffect> by EffectsDelegate() {

    fun onAction(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.OnFaveClick -> {
                viewModelScope.launch {
                    feedRepository.addAuthorToFave(
                        action.userId,
                        action.cardId,
                    )
                }
            }

            else -> {}
        }
    }

    suspend fun getFeed(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Home:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val authors = feedRepository.getAuthors(userId = userId)
            Log.d("efefe", "authors = $authors")
            val articles = feedRepository.getArticles(userId = userId)
            val poets = feedRepository.getPoets(userId = userId)
            updateState {
                copy(
                    userId = userId,
                    feed = Feed(authors, articles, poets),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(HomeViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}