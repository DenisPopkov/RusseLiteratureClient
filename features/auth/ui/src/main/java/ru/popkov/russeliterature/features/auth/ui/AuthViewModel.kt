package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import ru.popkov.russeliterature.features.auth.domain.usecase.ValidatePhoneNumber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
    private val validatePhoneNumber: ValidatePhoneNumber,
) : ViewModel(),
    StateProvider<AuthFormState> by StateDelegate(AuthFormState()),
    EffectsProvider<AuthViewEffect> by EffectsDelegate() {

    fun onAction(action: AuthViewAction) {
        when (action) {
            is AuthViewAction.OnAlreadyHaveAccountClick ->
                viewModelScope.sendEffect(AuthViewEffect.ChangeAuthToAlreadyHaveAccount)

            is AuthViewAction.OnApplyPhoneNumberClick -> {
                viewModelScope.launch {
                    updateState { copy(phoneNumber = action.phoneNumber) }
                }
                submitData()
            }

            is AuthViewAction.OnApplyPasswordClick -> {
                viewModelScope.launch {
                    updateState { copy(password = action.password) }
                }
                submitData()
            }

            is AuthViewAction.OnNoAccountClick ->
                viewModelScope.sendEffect(AuthViewEffect.ChangeAuthToNoAccount)
        }
    }

    private fun submitData() {
        val phoneNumberResult = validatePhoneNumber.invoke(state.value.phoneNumber)

        val hasError = listOf(
            phoneNumberResult
        ).any { it.errorMessage != null }

        if (hasError) {
            viewModelScope.sendEffect(
                AuthViewEffect.ShowError(
                    errorMessage = phoneNumberResult.errorMessage ?: ""
                )
            )
        }
    }

}