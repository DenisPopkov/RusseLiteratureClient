package ru.popkov.russeliterature.features.library.data.local.mappers

import ru.popkov.russeliterature.features.library.data.local.entities.Song
import kotlin.time.Duration.Companion.seconds

object SongMapper {

    fun Song.toDomain() =
        ru.popkov.russeliterature.features.library.domain.model.Song(
            id = id,
            title = title,
            duration = duration.seconds,
        )

}