package ru.popkov.russeliterature.features.auth.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.popkov.datastore.token.Token
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val token: Token
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val jwt = runBlocking { token.jwt.first().token }

        val request = chain.request().newBuilder()
            .addHeader("Authorization", jwt)
            .build()

        return chain.proceed(request)
    }
}
