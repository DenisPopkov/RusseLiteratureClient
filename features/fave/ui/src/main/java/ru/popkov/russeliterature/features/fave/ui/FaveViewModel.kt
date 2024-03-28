package ru.popkov.russeliterature.features.fave.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import javax.inject.Inject

class FaveViewModel @Inject constructor(

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
        }
    }
}