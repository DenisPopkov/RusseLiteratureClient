package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import auth.AuthOuterClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.popkov.android.core.feature.ui.EffectsDelegate
import ru.popkov.android.core.feature.ui.EffectsProvider
import ru.popkov.android.core.feature.ui.StateDelegate
import ru.popkov.android.core.feature.ui.StateProvider
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import ru.popkov.russeliterature.features.auth.domain.usecase.ValidatePassword
import ru.popkov.russeliterature.features.auth.domain.usecase.ValidatePhoneNumber
import timber.log.Timber
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

        when (state.value.authGlobalState) {
            AuthGlobalState.REGISTER_NEW_USER_PHONE_NUMBER -> {
                if (phoneNumberResult.errorMessage == null) {
                    updateState { copy(authGlobalState = AuthGlobalState.REGISTER_NEW_USER_PASSWORD) }
                } else {
                    viewModelScope.launch {
                        sendEffect(AuthViewEffect.ShowError(phoneNumberResult.errorMessage ?: ""))
                    }
                }
            }

            AuthGlobalState.REGISTER_NEW_USER_PASSWORD -> {
                viewModelScope.launch {
                    if (passwordResult.errorMessage == null) {
                        registerAndNavigateToMain()
                    } else {
                        sendEffect(AuthViewEffect.ShowError(passwordResult.errorMessage ?: ""))
                    }
                }
            }

            AuthGlobalState.AUTH -> {
                viewModelScope.launch {
                    if (phoneNumberResult.errorMessage == null && passwordResult.errorMessage == null) {
                        loginUserAndNavigateToMain()
                    } else {
                        if (phoneNumberResult.errorMessage != null) {
                            sendEffect(
                                AuthViewEffect.ShowError(
                                    phoneNumberResult.errorMessage ?: ""
                                )
                            )
                        } else {
                            sendEffect(AuthViewEffect.ShowError(passwordResult.errorMessage ?: ""))
                        }
                    }
                }
            }

        }
    }

    private suspend fun registerAndNavigateToMain() {
        registerNewUser(
            phoneNumber = state.value.phoneNumber,
            password = state.value.password
        ).invokeOnCompletion { error ->
            viewModelScope.launch {
                if (error != null) {
                    sendEffect(AuthViewEffect.ShowError("Пользователь с таким номером телефона уже зарегистрирован!"))
                } else {
                    loginUserAndNavigateToMain()
                }
            }
        }
    }

    private suspend fun loginUserAndNavigateToMain() {
        loginUser(
            phoneNumber = state.value.phoneNumber,
            password = state.value.password,
        ).invokeOnCompletion { error ->
            viewModelScope.launch {
                if (error != null) {
                    sendEffect(AuthViewEffect.ShowError("Неправильный номер телефона или пароль!"))
                } else {
                    sendEffect(AuthViewEffect.GoToMainScreen)
                }
            }
        }
    }

    private suspend fun registerNewUser(
        phoneNumber: String,
        password: String
    ): Deferred<AuthOuterClass.RegisterResponse> {
        val request = AuthOuterClass.RegisterRequest.newBuilder()
            .setPhone(phoneNumber)
            .setPassword(password)
            .build()

        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Auth").d("exception: %s", throwable)
        }

        return viewModelScope.async(handler) {
            authRepository.registerUser(request)
        }
    }

    private suspend fun loginUser(
        phoneNumber: String,
        password: String,
    ): Deferred<AuthOuterClass.LoginResponse> {
        val request = AuthOuterClass.LoginRequest
            .newBuilder()
            .setPhone(phoneNumber)
            .setPassword(password)
            .build()

        val handler = CoroutineExceptionHandler { _, throwable ->
            Timber.tag("Auth").d("exception: %s", throwable)
        }

        return viewModelScope.async(handler) {
            authRepository.loginUser(request)
        }
    }
}
