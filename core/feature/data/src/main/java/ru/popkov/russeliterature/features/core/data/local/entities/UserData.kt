package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String,
)