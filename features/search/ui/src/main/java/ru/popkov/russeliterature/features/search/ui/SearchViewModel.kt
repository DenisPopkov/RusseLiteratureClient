package ru.popkov.russeliterature.features.search.ui

import androidx.lifecycle.ViewModel
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import javax.inject.Inject

class SearchViewModel @Inject constructor(

) : ViewModel(),
    StateProvider<SearchState> by StateDelegate(SearchState()),
    EffectsProvider<SearchViewEffect> by EffectsDelegate() {

    fun onAction(action: SearchViewAction) {
        when (action) {

            else -> {}
        }
    }
}