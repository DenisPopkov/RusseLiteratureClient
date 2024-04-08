package ru.popkov.russeliterature.features.auth.domain.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Clip
import ru.popkov.russeliterature.features.auth.domain.model.Quiz

interface QuizRepository {
    suspend fun getQuiz(
        quizId: Long,
    ): Quiz

    suspend fun getClip(
        clipId: Long,
    ): Clip
}