package ru.popkov.russeliterature.features.quiz.ui

import androidx.compose.runtime.Immutable

@Immutable
data class QuizAnswer(
    val id: Int = 0,
    val answer: String = "",
    val isCorrect: Boolean = false,
)

@Immutable
data class QuizItem(
    val id: Int = 0,
    val quizQuestion: String = "В давние времена Нижний был центром соляной промышленности. В 1864 году соляные склады оказались пустыми.",
    val quizAnswer: String = "В то время действовала монополия на продажу соли, а нечистые на руку купцы потихоньку распродавали её и списывали убытки на паводки. Склады стояли у реки и, действительно, могли подновляться. Афера удачно складывалась до того, как Александр II отменил монополию и приказал срочно продать соль. Паводка в том году не было, так что свалить убытки было не на что. Участники аферы осудили по всей строгости закона. По городской легенде, причастному к скандалу купцу.",
    val answers: List<QuizAnswer> = listOf(
        QuizAnswer(
            id = 0,
            answer = "Признались в краже соли",
            isCorrect = true,
        ),
        QuizAnswer(
            id = 1,
            answer = "Паводок уничтожил соль",
            isCorrect = false,
        ),
        QuizAnswer(
            id = 2,
            answer = "Отгрузили соль в долг до паводка",
            isCorrect = false,
        ),
    ),
)