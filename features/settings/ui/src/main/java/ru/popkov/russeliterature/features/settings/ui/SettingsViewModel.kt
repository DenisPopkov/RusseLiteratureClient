package ru.popkov.russeliterature.features.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.datastore.token.Token
import ru.popkov.russeliterature.features.auth.domain.repositories.SettingsRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val tokenDatastore: Token,
) : ViewModel(),
    StateProvider<SettingsState> by StateDelegate(SettingsState()),
    EffectsProvider<SettingsViewEffect> by EffectsDelegate() {

    fun onAction(action: SettingsViewAction) {
        when (action) {
            is SettingsViewAction.OnDeleteAccountClick -> {
                viewModelScope.launch {
                    deleteUserAccount()
                }
            }

            is SettingsViewAction.OnExitAccountClick -> {
                viewModelScope.launch {
                    tokenDatastore.deleteToken()
                    sendEffect(SettingsViewEffect.OnExitAccountClick)
                }
            }
        }
    }

    private suspend fun deleteUserAccount() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Settings:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            settingsRepository.deleteUserAccount()
            updateState { copy(isLoading = false) }
            sendEffect(SettingsViewEffect.OnDeleteAccountClick)
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SettingsViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }

    suspend fun getSettings() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Settings:").d(throwable, "error occurred: %s", 0)
        }

        viewModelScope.launch(handler) {
            updateState { copy(isLoading = true) }
            val settings = settingsRepository.getSettings()
            updateState {
                copy(
                    userName = settings.name,
                    userImage = settings.image,
                    isLoading = false,
                )
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                if (it != null) {
                    updateState { copy(isLoading = false) }
                    sendEffect(SettingsViewEffect.ShowError("Произошла ошибка!"))
                }
            }
        }
    }
}