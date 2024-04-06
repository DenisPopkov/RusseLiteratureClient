package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz(
    @PrimaryKey
    val id: Long,
    val question: String,
    val description: String,
    val image: String,
    val answerId: Long,
)
