package ru.popkov.russeliterature.features.clip.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.QuizRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ClipViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val quizRepository: QuizRepository,
) : ViewModel(),
    StateProvider<ClipState> by StateDelegate(ClipState()),
    EffectsProvider<ClipViewEffect> by EffectsDelegate() {

    private val clipId = ClipDestination.Args(savedStateHandle).clipId

    fun onAction(action: ClipViewAction) {
        when (action) {
            ClipViewAction.OnToQuizClick -> {
                viewModelScope.launch {
                    sendEffect(ClipViewEffect.OnToQuizEffect)
                }
            }
        }
    }

    suspend fun getClip(userId: Long) {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Clip:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val clip = quizRepository.getClip(clipId ?: -1L)
            updateState {
                copy(
                    userId = userId,
                    clip = clip,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(ClipViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}