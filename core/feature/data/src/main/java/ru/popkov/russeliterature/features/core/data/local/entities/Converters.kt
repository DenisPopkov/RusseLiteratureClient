package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun fromClipTextList(value: List<ClipText>): String {
        val listType = Types.newParameterizedType(List::class.java, ClipText::class.java)
        val adapter = moshi.adapter<List<ClipText>>(listType)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toClipTextList(value: String): List<ClipText> {
        val listType = Types.newParameterizedType(List::class.java, ClipText::class.java)
        val adapter = moshi.adapter<List<ClipText>>(listType)
        return adapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun fromAnswerList(value: List<Answer>): String {
        val listType = Types.newParameterizedType(List::class.java, Answer::class.java)
        val adapter = moshi.adapter<List<Answer>>(listType)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toAnswerList(value: String): List<Answer> {
        val listType = Types.newParameterizedType(List::class.java, Answer::class.java)
        val adapter = moshi.adapter<List<Answer>>(listType)
        return adapter.fromJson(value) ?: emptyList()
    }
}
