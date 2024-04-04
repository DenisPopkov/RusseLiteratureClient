package ru.popkov.russeliterature.features.auth.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.popkov.russeliterature.features.auth.data.remote.dtos.Clip
import ru.popkov.russeliterature.features.auth.data.remote.dtos.Quiz

interface QuizApi {
    @GET("/quiz")
    suspend fun getQuiz(
        @Query("quizId") quizId: Long,
    ): Quiz

    @GET("/clip")
    suspend fun getClip(
        @Query("clipId") clipId: Long,
    ): Clip
}