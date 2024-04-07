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
    val clipId: Long,
    val text: List<ClipText>,
    val quiz: Quiz,
    val image: String,
)

@Keep
data class Quiz(
    val quizId: Long,
    val question: String,
    val description: String,
    val image: String,
    val answers: List<Answer>,
)

@Keep
data class Answer(
    val answerId: Long,
    val text: String,
//    val isRight: Boolean,
)

@Keep
data class ClipText(
    val title: String,
    val text: String,
)