package ru.popkov.russeliterature.features.auth.data.remote.mappers

import ru.popkov.russeliterature.features.auth.data.remote.dtos.Settings as SettingsDto
import ru.popkov.russeliterature.features.auth.domain.model.Settings as SettingsDomain

object SettingsMapper {

    fun SettingsDto.toEntity() =
        SettingsDomain(
            name = this.name,
            image = this.image,
        )
}