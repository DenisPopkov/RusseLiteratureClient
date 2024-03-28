package ru.popkov.russeliterature.features.quiz.ui

import androidx.lifecycle.ViewModel
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import javax.inject.Inject

class QuizViewModel @Inject constructor(

) : ViewModel(),
    StateProvider<QuizState> by StateDelegate(QuizState()),
    EffectsProvider<QuizViewEffect> by EffectsDelegate() {

    fun onAction(action: QuizViewAction) {
        when (action) {
            is QuizViewAction.OnAnswerClick -> {

            }
        }
    }
}