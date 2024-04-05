package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Feed(
    @PrimaryKey
    val id: Long,
    val authors: List<Author>,
    val articles: List<Article>,
    val poets: List<Poet>,
)
