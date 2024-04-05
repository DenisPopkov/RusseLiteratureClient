package ru.popkov.russeliterature.features.core.data.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Clip
import ru.popkov.russeliterature.features.auth.domain.model.Quiz
import ru.popkov.russeliterature.features.auth.domain.repositories.QuizRepository
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import ru.popkov.russeliterature.features.core.data.local.mappers.QuizMapper.toClipDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.QuizMapper.toQuizDomain
import ru.popkov.russeliterature.features.core.data.remote.api.QuizApi
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toClipEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toQuizEntity
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class QuizRepository @Inject constructor(
    private val quizApi: QuizApi,
    private val feedDao: FeedDao,
) : QuizRepository {
    override suspend fun getQuiz(quizId: Long): Quiz {
        val quiz = quizApi.getQuiz(quizId)
        feedDao.addQuiz(quiz.toQuizEntity())
        return feedDao.getQuiz().toQuizDomain()
    }

    override suspend fun getClip(clipId: Long): Clip {
        val clip = quizApi.getClip(clipId)
        feedDao.addClip(clip.toClipEntity())
        return feedDao.getClip().toClipDomain()
    }

}