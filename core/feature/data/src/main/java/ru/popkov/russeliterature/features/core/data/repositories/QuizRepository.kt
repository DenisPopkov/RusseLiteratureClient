package ru.popkov.russeliterature.features.core.data.repositories

import ru.popkov.russeliterature.features.auth.domain.model.Clip
import ru.popkov.russeliterature.features.auth.domain.model.Quiz
import ru.popkov.russeliterature.features.auth.domain.repositories.QuizRepository
import ru.popkov.russeliterature.features.core.data.local.daos.ClipDao
import ru.popkov.russeliterature.features.core.data.local.mappers.QuizMapper.toClipDomain
import ru.popkov.russeliterature.features.core.data.local.mappers.QuizMapper.toQuizDomain
import ru.popkov.russeliterature.features.core.data.remote.api.QuizApi
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toClipEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toListAnswerEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toListClipTextEntity
import ru.popkov.russeliterature.features.core.data.remote.mappers.QuizMapper.toQuizEntity
import se.ansman.dagger.auto.AutoBind
import javax.inject.Inject
import javax.inject.Singleton

@AutoBind
@Singleton
class QuizRepository @Inject constructor(
    private val quizApi: QuizApi,
    private val clipDao: ClipDao,
) : QuizRepository {
    override suspend fun getQuiz(quizId: Long): Quiz {
        val quiz = quizApi.getQuiz(quizId)
        clipDao.addAnswers(*quiz.answers.toListAnswerEntity().toTypedArray())
        clipDao.addQuiz(quiz.toQuizEntity())
        return clipDao.getQuiz(quizId).toQuizDomain()
    }

    override suspend fun getClip(clipId: Long): Clip {
        val clip = quizApi.getClip(clipId)
        clipDao.addClipTexts(*clip.clipTexts.toListClipTextEntity().toTypedArray())
        clipDao.addClip(clip.toClipEntity())
        return clipDao.getClip(clipId).toClipDomain()
    }

}