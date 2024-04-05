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
    val answers: List<Answer>,
)

@Entity
data class Answer(
    @PrimaryKey
    val id: Long,
    val text: String,
    val isRight: Boolean,
)

@Entity
data class ClipText(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val text: String,
)
