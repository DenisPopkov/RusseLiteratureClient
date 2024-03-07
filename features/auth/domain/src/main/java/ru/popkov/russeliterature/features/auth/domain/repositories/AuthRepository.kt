package ru.popkov.russeliterature.features.auth.domain.repositories

interface AuthRepository {

    // TODO() actualize method return call when backend will be ready
    suspend fun loginUser(): Boolean

    suspend fun isUserLogged(id: Long): Long
}