package ru.popkov.russeliterature.features.clip.ui

import androidx.lifecycle.ViewModel
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import javax.inject.Inject

class ClipViewModel @Inject constructor(

) : ViewModel(),
    StateProvider<ClipState> by StateDelegate(ClipState()),
    EffectsProvider<ClipViewEffect> by EffectsDelegate() {

    fun onAction(action: ClipViewAction) {
        when (action) {

            else -> {}
        }
    }
}