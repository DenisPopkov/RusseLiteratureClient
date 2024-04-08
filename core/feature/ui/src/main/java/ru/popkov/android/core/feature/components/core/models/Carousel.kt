package ru.popkov.android.core.feature.components.core.models

import androidx.compose.runtime.Immutable

@Immutable
data class Carousel(
    val id: Long,
    val articleTitle: String,
    val articleDescription: String,
    val articleImage: String,
)