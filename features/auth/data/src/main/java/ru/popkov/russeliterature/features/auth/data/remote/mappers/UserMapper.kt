package ru.popkov.russeliterature.features.auth.data.remote.mappers

import ru.popkov.russeliterature.features.auth.data.local.entities.User as UserEntity
import ru.popkov.russeliterature.features.auth.data.remote.dtos.User as UserDto

object UserMapper {

    fun UserDto.toEntity(userId: Long) =
        UserEntity(
            id = userId,
        )

}