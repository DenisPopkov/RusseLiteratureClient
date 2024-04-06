package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClipText(
    @PrimaryKey
    val id: Long,
    val title: String,
    val text: String,
)
