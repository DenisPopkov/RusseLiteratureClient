package ru.popkov.russeliterature.features.core.data.repositories

import ru.popkov.russeliterature.features.core.data.remote.api.QuizApi
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toClipDomain
import ru.popkov.russeliterature.features.core.data.remote.mappers.FeedMapper.toQuizDomain
import ru.popkov.russeliterature.features.auth.domain.model.Clip
import ru.popkov.russeliterature.features.auth.domain.model.Quiz
import ru.popkov.russeliterature.features.auth.domain.repositories.QuizRepository
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class QuizRepository @Inject constructor(
    private val quizApi: QuizApi,
) : QuizRepository {
    override suspend fun getQuiz(quizId: Long): Quiz {
        return quizApi.getQuiz(quizId).toQuizDomain()
    }

    override suspend fun getClip(clipId: Long): Clip {
        return quizApi.getClip(clipId).toClipDomain()
    }

}