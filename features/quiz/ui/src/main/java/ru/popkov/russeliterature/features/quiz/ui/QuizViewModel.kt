package ru.popkov.russeliterature.features.quiz.ui

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
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val quizRepository: QuizRepository,
) : ViewModel(),
    StateProvider<QuizState> by StateDelegate(QuizState()),
    EffectsProvider<QuizViewEffect> by EffectsDelegate() {

    private val quizId = QuizDestination.Args(savedStateHandle).quizId

    fun onAction(action: QuizViewAction) {
        when (action) {
            is QuizViewAction.OnAnswerClick -> {
                viewModelScope.launch {
                    updateState { copy(quizState = QuizScreenState.RESULTS) }
                }
            }

            QuizViewAction.OnCloseClick -> {
                viewModelScope.launch {
                    sendEffect(QuizViewEffect.OnCloseEffect)
                }
            }
        }
    }

    suspend fun getQuiz() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Quiz:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val quiz = quizRepository.getQuiz(quizId ?: -1L)
            updateState {
                copy(
                    userId = userId,
                    quizState = QuizScreenState.QUESTION,
                    quiz = quiz,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(QuizViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}
