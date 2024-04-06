package ru.popkov.russeliterature.features.auth.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class Author(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean
)

@Keep
data class Article(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean
)

@Keep
data class Poet(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean
)

@Keep
data class Clip(
    val id: Long,
    val text: Long,
    val quiz: Long,
)

@Keep
data class Quiz(
    val id: Long,
    val question: String,
    val description: String,
    val image: String,
    val answers: Long,
)

@Keep
data class Answer(
    val id: Long,
    val text: String,
    val isRight: Boolean,
)

@Keep
data class ClipText(
    val title: String,
    val text: String,
)