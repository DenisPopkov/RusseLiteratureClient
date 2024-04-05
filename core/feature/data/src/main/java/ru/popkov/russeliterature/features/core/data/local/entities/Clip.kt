package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Clip(
    @PrimaryKey
    val id: Long,
    val text: List<ClipText>,
    val quiz: Long // quiz id
)
