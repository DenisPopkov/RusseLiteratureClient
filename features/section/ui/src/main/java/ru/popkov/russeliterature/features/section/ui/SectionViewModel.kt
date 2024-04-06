package ru.popkov.russeliterature.features.section.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.components.core.models.SectionType
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val feedRepository: FeedRepository
) : ViewModel(),
    StateProvider<SectionState> by StateDelegate(SectionState()),
    EffectsProvider<SectionViewEffect> by EffectsDelegate() {

    private val sectionId = SectionDestination.Args(savedStateHandle).sectionType
    val sectionType = SectionType.entries[(sectionId ?: 0) + 1]

    fun onAction(action: SectionViewAction) {
        when (action) {
            is SectionViewAction.OnAuthorFaveClick -> {
                viewModelScope.launch {
                    val authors = feedRepository.addAuthorToFave(
                        action.userId,
                        action.authorId,
                    )
                    updateState { copy(authors = authors) }
                }
            }

            is SectionViewAction.OnArticleFaveClick -> {
                viewModelScope.launch {
                    val articles = feedRepository.addArticleToFave(
                        action.userId,
                        action.articleId,
                    )
                    updateState { copy(articles = articles) }
                }
            }

            is SectionViewAction.OnPoetFaveClick -> {
                viewModelScope.launch {
                    val poets = feedRepository.addPoetToFave(
                        action.userId,
                        action.poetId,
                    )
                    updateState { copy(poets = poets) }
                }
            }

            is SectionViewAction.OnBackClick -> {
                viewModelScope.launch {
                    sendEffect(SectionViewEffect.OnBackClick)
                }
            }

            else -> {}
        }
    }

    suspend fun getAuthors(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Section:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val authors = feedRepository.getAuthorsFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = authors,
                    articles = null,
                    poets = null,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SectionViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    suspend fun getArticles(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Section:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val articles = feedRepository.getArticlesFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = null,
                    articles = articles,
                    poets = null,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SectionViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    suspend fun getPoets(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Section:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val poets = feedRepository.getPoetsFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = null,
                    articles = null,
                    poets = poets,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SectionViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}