package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
) : ViewModel(),
    StateProvider<AuthFormState> by StateDelegate(AuthFormState()),
    EffectsProvider<AuthViewEffect> by EffectsDelegate() {

    fun onAction(action: AuthViewAction) {
        when (action) {
            is AuthViewAction.OnAlreadyHaveAccountClick ->
                viewModelScope.sendEffect(AuthViewEffect.ChangeAuthToAlreadyHaveAccount)

            is AuthViewAction.OnApplyPasswordClick ->
                viewModelScope.sendEffect(AuthViewEffect.ValidateField)

            is AuthViewAction.OnApplyPhoneNumberClick ->
                viewModelScope.sendEffect(AuthViewEffect.ValidateField)

            is AuthViewAction.OnNoAccountClick ->
                viewModelScope.sendEffect(AuthViewEffect.ChangeAuthToNoAccount)
        }
    }

}