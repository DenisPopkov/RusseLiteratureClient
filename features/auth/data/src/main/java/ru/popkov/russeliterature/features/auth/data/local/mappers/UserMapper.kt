package ru.popkov.russeliterature.features.auth.data.local.mappers

import ru.popkov.russeliterature.features.auth.data.local.entities.User as UserEntity
import ru.popkov.russeliterature.features.auth.domain.model.User

object UserMapper {

    fun UserEntity.toDomain() =
        User(
            userId = id,
        )

}