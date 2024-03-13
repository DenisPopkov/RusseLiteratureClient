package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import auth.AuthOuterClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import ru.popkov.russeliterature.features.auth.domain.usecase.ValidatePassword
import ru.popkov.russeliterature.features.auth.domain.usecase.ValidatePhoneNumber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val validatePassword: ValidatePassword,
) : ViewModel(),
    StateProvider<AuthFormState> by StateDelegate(AuthFormState()),
    EffectsProvider<AuthViewEffect> by EffectsDelegate() {

    fun onAction(action: AuthViewAction) {
        when (action) {
            is AuthViewAction.OnAlreadyHaveAccountClick ->
                viewModelScope.launch {
                    updateState { copy(authGlobalState = AuthGlobalState.AUTH) }
                }

            is AuthViewAction.OnPhoneNumberChange -> {
                viewModelScope.launch {
                    updateState { copy(phoneNumber = action.phoneNumber) }
                }
            }

            is AuthViewAction.OnPasswordChange -> {
                viewModelScope.launch {
                    updateState { copy(password = action.password) }
                }
            }

            is AuthViewAction.OnNoAccountClick ->
                viewModelScope.launch {
                    updateState {
                        copy(
                            authGlobalState = if (state.value.authGlobalState == AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER) {
                                AuthGlobalState.REGISTER_NEW_USER_PASSWORD
                            } else {
                                AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER
                            }
                        )
                    }
                }

            is AuthViewAction.OnDone -> submitData()
        }
    }

    private fun submitData() {
        val phoneNumberResult = validatePhoneNumber.invoke(state.value.phoneNumber)
        val passwordResult = validatePassword.invoke(state.value.password)

        viewModelScope.launch {
            when (state.value.authGlobalState) {
                AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER -> {
                    if (phoneNumberResult.errorMessage == null) {
                        updateState { copy(authGlobalState = AuthGlobalState.REGISTER_NEW_USER_PASSWORD) }
                    } else {
                        sendEffect(AuthViewEffect.ShowError(phoneNumberResult.errorMessage ?: ""))
                    }
                }

                AuthGlobalState.REGISTER_NEW_USER_PASSWORD -> {
                    if (passwordResult.errorMessage == null) {
                        registerAndNavigateToMain()
                    } else {
                        sendEffect(AuthViewEffect.ShowError(passwordResult.errorMessage ?: ""))
                    }
                }

                AuthGlobalState.AUTH -> {
                    if (phoneNumberResult.errorMessage == null && passwordResult.errorMessage == null) {
                        loginUserAndNavigateToMain()
                    } else {
                        sendEffect(AuthViewEffect.ShowError("Неправильный номер телефона или пароль"))
                    }
                }

            }
        }
    }

    private suspend fun registerAndNavigateToMain() = coroutineScope {
        registerNewUser(
            phoneNumber = state.value.phoneNumber,
            password = state.value.password
        ).invokeOnCompletion {
            sendEffect(AuthViewEffect.GoToMainScreen)
        }
    }

    private suspend fun loginUserAndNavigateToMain() = coroutineScope {
        loginUser(
            phoneNumber = state.value.phoneNumber,
            password = state.value.password,
        ).invokeOnCompletion {
            sendEffect(AuthViewEffect.GoToMainScreen)
        }
    }

    private suspend fun registerNewUser(
        phoneNumber: String,
        password: String
    ): Deferred<AuthOuterClass.RegisterResponse> = coroutineScope {
        val request = AuthOuterClass.RegisterRequest
            .newBuilder()
            .setPhone(phoneNumber)
            .setPassword(password)
            .build()
        return@coroutineScope async { authRepository.registerUser(request) }
    }

    private suspend fun loginUser(
        phoneNumber: String,
        password: String,
    ): Deferred<AuthOuterClass.LoginResponse> = coroutineScope {
        val request = AuthOuterClass.LoginRequest
            .newBuilder()
            .setPhone(phoneNumber)
            .setPassword(password)
            .build()
        return@coroutineScope async { authRepository.loginUser(request) }
    }

}