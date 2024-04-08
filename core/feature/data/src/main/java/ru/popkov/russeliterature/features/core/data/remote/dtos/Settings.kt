package ru.popkov.russeliterature.features.core.data.remote.dtos

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Settings(
    val name: String,
    val image: String,
)
