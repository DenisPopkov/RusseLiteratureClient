package ru.popkov.russeliterature.features.auth.data.repositories

import auth.AuthGrpc
import auth.AuthOuterClass
import auth.AuthOuterClass.LoginRequest
import io.grpc.ManagedChannelBuilder
import ru.popkov.russeliterature.features.auth.data.local.daos.UserDao
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class DefaultUserRepository @Inject constructor(
    private val userDao: UserDao,
) : AuthRepository {

    override suspend fun registerUser(registerRequest: AuthOuterClass.RegisterRequest): AuthOuterClass.RegisterResponse {
        val channel = ManagedChannelBuilder.forAddress("192.168.88.112", 8085).usePlaintext().build()
        val client = AuthGrpc.newBlockingStub(channel)
        return client.register(registerRequest)
    }

    // for adb is 10.0.2.2
    // for real device is 192.168.88.112 (office network on laptop)
    override suspend fun loginUser(loginRequest: LoginRequest): AuthOuterClass.LoginResponse {
        val channel = ManagedChannelBuilder.forAddress("192.168.88.112", 8085).usePlaintext().build()
        val client = AuthGrpc.newBlockingStub(channel)
        return client.login(loginRequest)
    }
}