package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(
    @PrimaryKey
    val answerId: Long,
    val text: String,
//    val isRight: Boolean,
)
