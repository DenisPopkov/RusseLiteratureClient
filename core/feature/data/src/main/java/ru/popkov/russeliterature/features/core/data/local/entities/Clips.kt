package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

data class ClipTextWrapper(val clipTextList: List<ClipText>)

@Entity
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class Clips(
    @PrimaryKey
    val clipId: Long,
    @Embedded
    val text: ClipTextWrapper,
    @Embedded
    val quiz: Quiz,
    val clipImage: String,
)
