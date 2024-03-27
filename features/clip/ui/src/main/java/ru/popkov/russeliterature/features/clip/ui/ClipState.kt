package ru.popkov.russeliterature.features.clip.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class ClipState(
    val clipItems: List<ClipItem> = listOf(
        ClipItem(
            id = 0,
            clipTitle = "ранние годы",
            clipDescription = "Биография Федора Михайловича Достоевского началась 11 ноября 1821 года. За почти шесть десятков лет писатель прошел сложный путь: сидел в тюрьме, был на каторге, эмигрировал, проигрывался, скрывался от кредиторов, терял родных и близких. Но никогда не переставал писать. Все события из жизни Достоевский прямо или косвенно переносил в свои книги. Наверное, поэтому герои его произведений настолько отличаются друг от друга и принадлежат к разным социальным слоям.",
        ),
        ClipItem(
            id = 0,
            clipTitle = "дебют",
            clipDescription = "В конце мая 1845 года Федор Достоевский закончил свой первый роман «Бедные люди». Произведение восторженно приняли законодатели литературной моды тех лет — Николай Некрасов и Виссарион Белинский. Некрасов назвал начинающего писателя «новым Гоголем» и опубликовал роман в своем альманахе «Петербургский сборник».",
        ),
        ClipItem(
            id = 0,
            clipTitle = "каторга",
            clipDescription = "Впечатления от жизни в Омском остроге Федор Достоевский выразил в «Записках из Мертвого дома». Это произведение русской литературы стало одним из первых, рассказывающих о каторге и жизни заключенных, их быте и нравах. Для современников Достоевского «Записки из Мертвого дома» стали настоящим откровением. Иван Тургенев сравнивал произведение с «Адом» Данте, Александр Герцен — с фреской «Страшный суд» работы Микеланджело. О жанре «Записок» литературоведы спорят до сих пор: с одной стороны, произведение строится на воспоминаниях автора и могло бы считаться мемуарами, с другой — Достоевский ввел в повесть вымышленного героя и не всегда придерживался фактической и хронологической точности.",
        ),
    ),
)
