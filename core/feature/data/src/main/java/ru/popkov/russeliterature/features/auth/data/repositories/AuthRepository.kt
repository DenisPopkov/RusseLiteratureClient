package ru.popkov.russeliterature.features.auth.data.repositories

import auth.AuthGrpc
import auth.AuthOuterClass
import auth.AuthOuterClass.LoginRequest
import auth.AuthOuterClass.LoginResponse
import auth.AuthOuterClass.RegisterResponse
import io.grpc.ManagedChannelBuilder
import ru.popkov.datastore.token.Token
import ru.popkov.datastore.user.User
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class AuthRepository @Inject constructor(
    private val dataStore: Token,
    private val userDataStore: User,
) : AuthRepository {

    override suspend fun registerUser(registerRequest: AuthOuterClass.RegisterRequest): RegisterResponse {
        val channel = ManagedChannelBuilder.forAddress("10.0.2.2", 4040).usePlaintext().build()
        val client = AuthGrpc.newBlockingStub(channel)
        val register = client.register(registerRequest)
        userDataStore.saveUserId(register.userId)
        return register
    }

    // for adb is 10.0.2.2
    // for real device is 192.168.88.112 (office network on laptop)
    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse {
        val channel = ManagedChannelBuilder.forAddress("10.0.2.2", 4040).usePlaintext().build()
        val client = AuthGrpc.newBlockingStub(channel)
        val userJWT = client.login(loginRequest)
        dataStore.saveToken(userJWT.token)
        return userJWT
    }
}