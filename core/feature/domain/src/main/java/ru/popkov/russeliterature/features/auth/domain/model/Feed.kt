package ru.popkov.russeliterature.features.auth.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class Feed(
    val authors: List<Author>,
    val articles: List<Article>,
    val poets: List<Poet>,
)

@Keep
data class Author(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Int,
    val isFave: Boolean
)

@Keep
data class Article(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val clip: Int,
    val isFave: Boolean
)

@Keep
data class Poet(
    val id: Long,
    val name: String,
    val image: String,
    val clip: Int,
    val isFave: Boolean
)

@Keep
data class Clip(
    val id: Long,
    val text: List<ClipText>,
    val quiz: Quiz
)

@Keep
data class Quiz(
    val id: Long,
    val question: String,
    val description: String,
    val image: String,
    val answers: List<Answer>,
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