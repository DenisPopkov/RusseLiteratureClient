package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _isUserLogged = MutableStateFlow(false)
    val isUserLogged = _isUserLogged

    fun checkUser() {
        runBlocking { _isUserLogged.value = authRepository.isUserLogged(10L) == 10L }
    }

    fun loginUser() {
        runBlocking {
            val result = authRepository.loginUser()
            _isUserLogged.value = result
        }
    }

}