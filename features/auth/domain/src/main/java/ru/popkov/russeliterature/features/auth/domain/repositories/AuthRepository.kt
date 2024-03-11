package ru.popkov.russeliterature.features.auth.domain.repositories

import auth.AuthOuterClass
import auth.AuthOuterClass.LoginRequest

interface AuthRepository {

    suspend fun registerUser(registerRequest: AuthOuterClass.RegisterRequest): AuthOuterClass.RegisterResponse
    suspend fun loginUser(loginRequest: LoginRequest): AuthOuterClass.LoginResponse
}