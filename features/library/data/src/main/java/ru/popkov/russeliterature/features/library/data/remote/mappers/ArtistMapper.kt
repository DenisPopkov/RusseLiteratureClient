package ru.popkov.russeliterature.features.library.data.remote.mappers

import ru.popkov.russeliterature.features.library.data.local.entities.Artist as ArtistEntity
import ru.popkov.russeliterature.features.library.data.remote.dtos.Artist as ArtistDto

object ArtistMapper {

    fun ArtistDto.toEntity() =
        ArtistEntity(
            id = 0,
            name = name,
        )

}