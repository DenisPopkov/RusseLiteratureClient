package ru.popkov.russeliterature.features.fave.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FaveViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
) : ViewModel(),
    StateProvider<FaveState> by StateDelegate(FaveState()),
    EffectsProvider<FaveViewEffect> by EffectsDelegate() {

    fun onAction(action: FaveViewAction) {
        when (action) {
            is FaveViewAction.OnMainScreenClick -> {
                viewModelScope.launch {
                    sendEffect(FaveViewEffect.GoToMainScreen)
                }
            }

            is FaveViewAction.OnAuthorFaveClick -> {
                viewModelScope.launch {
                    val authors = feedRepository.addAuthorToFave(
                        action.userId,
                        action.authorId,
                    )
                    updateState { copy(authors = authors) }
                }
            }

            is FaveViewAction.OnArticleFaveClick -> {
                viewModelScope.launch {
                    val articles = feedRepository.addArticleToFave(
                        action.userId,
                        action.articleId,
                    )
                    updateState { copy(articles = articles) }
                }
            }

            is FaveViewAction.OnPoetFaveClick -> {
                viewModelScope.launch {
                    val poets = feedRepository.addPoetToFave(
                        action.userId,
                        action.poetId,
                    )
                    updateState { copy(poets = poets) }
                }
            }

            is FaveViewAction.OnSectionClick -> {
                viewModelScope.launch {
                    sendEffect(FaveViewEffect.OnSectionClick(action.sectionId))
                }
            }
        }
    }

    suspend fun getFave(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Fave:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val authors = feedRepository.getFaveAuthors()
            val articles = feedRepository.getFaveArticles()
            val poets = feedRepository.getFavePoets()
            updateState {
                copy(
                    userId = userId,
                    authors = authors,
                    articles = articles,
                    poets = poets,
                    isEmptyState = authors.isEmpty() && articles.isEmpty() && poets.isEmpty(),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(FaveViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}