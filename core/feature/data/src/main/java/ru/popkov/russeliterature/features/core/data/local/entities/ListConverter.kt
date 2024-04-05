package ru.popkov.russeliterature.features.core.data.local.entities

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ListConverter {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromAuthorsList(authors: List<Author>): String {
        val listType = Types.newParameterizedType(List::class.java, Author::class.java)
        val jsonAdapter = moshi.adapter<List<Author>>(listType)
        return jsonAdapter.toJson(authors)
    }

    @TypeConverter
    fun toAuthorsList(authorsString: String): List<Author> {
        val listType = Types.newParameterizedType(List::class.java, Author::class.java)
        val jsonAdapter = moshi.adapter<List<Author>>(listType)
        return jsonAdapter.fromJson(authorsString) ?: emptyList()
    }

    @TypeConverter
    fun fromArticlesList(articles: List<Article>): String {
        val listType = Types.newParameterizedType(List::class.java, Article::class.java)
        val jsonAdapter = moshi.adapter<List<Article>>(listType)
        return jsonAdapter.toJson(articles)
    }

    @TypeConverter
    fun toArticlesList(articlesString: String): List<Article> {
        val listType = Types.newParameterizedType(List::class.java, Article::class.java)
        val jsonAdapter = moshi.adapter<List<Article>>(listType)
        return jsonAdapter.fromJson(articlesString) ?: emptyList()
    }

    @TypeConverter
    fun fromPoetsList(poets: List<Poet>): String {
        val listType = Types.newParameterizedType(List::class.java, Poet::class.java)
        val jsonAdapter = moshi.adapter<List<Poet>>(listType)
        return jsonAdapter.toJson(poets)
    }

    @TypeConverter
    fun toPoetsList(poetsString: String): List<Poet> {
        val listType = Types.newParameterizedType(List::class.java, Poet::class.java)
        val jsonAdapter = moshi.adapter<List<Poet>>(listType)
        return jsonAdapter.fromJson(poetsString) ?: emptyList()
    }

    @TypeConverter
    fun fromQuiz(quiz: Quiz): String {
        val jsonAdapter = moshi.adapter(Quiz::class.java)
        return jsonAdapter.toJson(quiz)
    }

    @TypeConverter
    fun toQuiz(quizString: String): Quiz {
        val jsonAdapter = moshi.adapter(Quiz::class.java)
        return jsonAdapter.fromJson(quizString) ?: Quiz(0, "", "", "", emptyList())
    }

    @TypeConverter
    fun fromAnswersList(answers: List<Answer>): String {
        val listType = Types.newParameterizedType(List::class.java, Answer::class.java)
        val jsonAdapter = moshi.adapter<List<Answer>>(listType)
        return jsonAdapter.toJson(answers)
    }

    @TypeConverter
    fun toAnswersList(answersString: String): List<Answer> {
        val listType = Types.newParameterizedType(List::class.java, Answer::class.java)
        val jsonAdapter = moshi.adapter<List<Answer>>(listType)
        return jsonAdapter.fromJson(answersString) ?: emptyList()
    }

    @TypeConverter
    fun fromClipText(clipText: ClipText): String {
        val jsonAdapter = moshi.adapter(ClipText::class.java)
        return jsonAdapter.toJson(clipText)
    }

    @TypeConverter
    fun toClipText(clipTextString: String): ClipText {
        val jsonAdapter = moshi.adapter(ClipText::class.java)
        return jsonAdapter.fromJson(clipTextString) ?: ClipText(0, "", "")
    }

    @TypeConverter
    fun fromClip(clip: Clip): String {
        val jsonAdapter = moshi.adapter(Clip::class.java)
        return jsonAdapter.toJson(clip)
    }

    @TypeConverter
    fun toClip(clipString: String): Clip {
        val jsonAdapter = moshi.adapter(Clip::class.java)
        return jsonAdapter.fromJson(clipString) ?: Clip(0, emptyList(), 0)
    }

    @TypeConverter
    fun fromClipTextList(clipTextList: List<ClipText>): String {
        val listType = Types.newParameterizedType(List::class.java, ClipText::class.java)
        val jsonAdapter = moshi.adapter<List<ClipText>>(listType)
        return jsonAdapter.toJson(clipTextList)
    }

    @TypeConverter
    fun toClipTextList(clipTextListString: String): List<ClipText> {
        val listType = Types.newParameterizedType(List::class.java, ClipText::class.java)
        val jsonAdapter = moshi.adapter<List<ClipText>>(listType)
        return jsonAdapter.fromJson(clipTextListString) ?: emptyList()
    }
}