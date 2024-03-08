package ru.popkov.russeliterature.features.auth.data.repositories

import kotlinx.coroutines.coroutineScope
import ru.popkov.russeliterature.features.auth.data.local.daos.UserDao
import ru.popkov.russeliterature.features.auth.data.remote.api.UserApi
import ru.popkov.russeliterature.features.auth.domain.repositories.AuthRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton
import ru.popkov.russeliterature.features.auth.data.local.entities.User as UserEntity

@AutoBind
@Singleton
class DefaultUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi,
) : AuthRepository {

    override suspend fun loginUser(): Boolean = coroutineScope {
//        val result = async { userApi.loginUser() }
        userDao.add(UserEntity(id = 10L))
        true
    }

    override suspend fun isUserLogged(id: Long): Long {
        return userDao.findUser(id)
    }
}