package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val clip: Int,
    val isFave: Boolean,
)
