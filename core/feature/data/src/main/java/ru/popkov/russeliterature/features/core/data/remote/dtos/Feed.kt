package ru.popkov.russeliterature.features.core.data.remote.dtos

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Feed(
    val authors: List<Author>,
    val articles: List<Article>,
    val poets: List<Poet>,
)

@Keep
@JsonClass(generateAdapter = true)
data class Author(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    @Json(name = "is_fave")
    val isFave: String
)

@Keep
@JsonClass(generateAdapter = true)
data class Article(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val clip: Long,
    @Json(name = "is_fave")
    val isFave: String
)

@Keep
@JsonClass(generateAdapter = true)
data class Poet(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Long,
    @Json(name = "is_fave")
    val isFave: String
)

@Keep
@JsonClass(generateAdapter = true)
data class Clip(
    val id: Long,
    val text: List<ClipText>,
    val quiz: Long
)

@Keep
@JsonClass(generateAdapter = true)
data class Quiz(
    val id: Long,
    val question: String,
    val description: String,
    val image: String,
    val answers: List<Answer>,
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
