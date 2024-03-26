package ru.popkov.russeliterature.features.settings.ui

import androidx.lifecycle.ViewModel
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import javax.inject.Inject

class SettingsViewModel @Inject constructor(

) : ViewModel(),
    StateProvider<SettingsState> by StateDelegate(SettingsState()),
    EffectsProvider<SettingsViewEffect> by EffectsDelegate() {

    fun onAction(action: SettingsViewAction) {
        when (action) {

            else -> {}
        }
    }
}