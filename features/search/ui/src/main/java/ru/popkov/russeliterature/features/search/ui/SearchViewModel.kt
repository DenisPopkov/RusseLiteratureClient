package ru.popkov.russeliterature.features.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.components.core.models.SectionFilterItem
import ru.popkov.android.core.feature.components.core.models.SectionType
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.FeedRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val feedRepository: FeedRepository,
) : ViewModel(),
    StateProvider<SearchState> by StateDelegate(SearchState()),
    EffectsProvider<SearchViewEffect> by EffectsDelegate() {

    fun onAction(action: SearchViewAction) {
        when (action) {

            is SearchViewAction.OnMainScreenClick -> {
                viewModelScope.launch {
                    sendEffect(SearchViewEffect.GoToMainScreen)
                }
            }

            is SearchViewAction.OnAuthorFaveClick -> {
                viewModelScope.launch {
                    val authors = feedRepository.addAuthorToFave(
                        action.userId,
                        action.authorId,
                    )
                    updateState { copy(authors = authors) }
                }
            }

            is SearchViewAction.OnArticleFaveClick -> {
                viewModelScope.launch {
                    val articles = feedRepository.addArticleToFave(
                        action.userId,
                        action.articleId,
                    )
                    updateState { copy(articles = articles) }
                }
            }

            is SearchViewAction.OnPoetFaveClick -> {
                viewModelScope.launch {
                    val poets = feedRepository.addPoetToFave(
                        action.userId,
                        action.poetId,
                    )
                    updateState { copy(poets = poets) }
                }
            }

            is SearchViewAction.OnSectionItemClick -> {
                viewModelScope.launch {
                    val updatedFilter = updateFilter(action.sectionType)
                    setFilter(sectionType = action.sectionType, userId = state.value.userId)
                    updateState { copy(filterList = updatedFilter) }
                }
            }

            is SearchViewAction.OnSearchChange -> {
                viewModelScope.launch {
                    getAll(userId = state.value.userId, filter = action.searchText)
                }
            }
        }
    }

    private fun updateFilter(sectionType: SectionType): List<SectionFilterItem> {
        val res = state.value.filterList.map {
            it.isSectionSelected = (it.sectionType == sectionType)
            it
        }
        return res
    }

    private suspend fun setFilter(sectionType: SectionType, userId: Long) {
        when (sectionType) {
            SectionType.ALL -> getAll(userId)
            SectionType.AUTHOR -> getAuthors(userId)
            SectionType.ARTICLE -> getArticles(userId)
            else -> getPoets(userId)
        }
    }

    suspend fun getAll(userId: Long, filter: String? = null) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Search:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val authors = feedRepository.getAuthorsFromLocal()
            val articles = feedRepository.getArticlesFromLocal()
            val poets = feedRepository.getPoetsFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = authors.filter { it.name.contains(filter ?: it.name) },
                    articles = articles.filter { it.name.contains((filter ?: it.name)) },
                    poets = poets.filter { it.name.contains((filter ?: it.name)) },
                    isEmptyState = authors.isEmpty() && articles.isEmpty() && poets.isEmpty(),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SearchViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    private suspend fun getAuthors(userId: Long, filter: String? = null) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Search:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val authors = feedRepository.getAuthorsFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = authors.filter { it.name.contains((filter ?: it.name)) },
                    articles = null,
                    poets = null,
                    isEmptyState = authors.isEmpty(),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SearchViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    private suspend fun getArticles(userId: Long, filter: String? = null) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Search:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val articles = feedRepository.getArticlesFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = null,
                    articles = articles.filter { it.name.contains((filter ?: it.name)) },
                    poets = null,
                    isEmptyState = articles.isEmpty(),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SearchViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    private suspend fun getPoets(userId: Long, filter: String? = null) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Search:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val poets = feedRepository.getPoetsFromLocal()
            updateState {
                copy(
                    userId = userId,
                    authors = null,
                    articles = null,
                    poets = poets.filter { it.name.contains((filter ?: it.name)) },
                    isEmptyState = poets.isEmpty(),
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SearchViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}