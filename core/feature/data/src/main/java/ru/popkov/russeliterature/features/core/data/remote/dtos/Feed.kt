package ru.popkov.russeliterature.features.core.data.remote.dtos

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Author(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean,
)

@Keep
@JsonClass(generateAdapter = true)
data class Article(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean,
)

@Keep
@JsonClass(generateAdapter = true)
data class Poet(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    val isFave: Boolean,
)

@Keep
@JsonClass(generateAdapter = true)
data class Clip(
    val id: Long,
    val textId: Long,
    val quiz: Long,
)

@Keep
@JsonClass(generateAdapter = true)
data class Quiz(
    val id: Long,
    val question: String,
    val description: String,
    val image: String,
    val answerId: Long,
)

@Keep
@JsonClass(generateAdapter = true)
data class Answer(
    val id: Long,
    val text: String,
    val isRight: Boolean,
)

@Keep
@JsonClass(generateAdapter = true)
data class ClipText(
    val title: String,
    val text: String,
)
