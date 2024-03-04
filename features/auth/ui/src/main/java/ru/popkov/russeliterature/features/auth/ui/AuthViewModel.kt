package ru.popkov.russeliterature.features.auth.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {

    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth

    fun loginUser() {
        _isAuth.value = true
    }
}