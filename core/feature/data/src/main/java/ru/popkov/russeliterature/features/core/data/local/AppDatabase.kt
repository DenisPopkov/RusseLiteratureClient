package ru.popkov.russeliterature.features.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import ru.popkov.russeliterature.features.core.data.local.entities.Answer
import ru.popkov.russeliterature.features.core.data.local.entities.Article
import ru.popkov.russeliterature.features.core.data.local.entities.Author
import ru.popkov.russeliterature.features.core.data.local.entities.Clip
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText
import ru.popkov.russeliterature.features.core.data.local.entities.Feed
import ru.popkov.russeliterature.features.core.data.local.entities.ListConverter
import ru.popkov.russeliterature.features.core.data.local.entities.Poet
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz

@Database(
    entities = [
        Feed::class,
        Author::class,
        Article::class,
        Poet::class,
        Clip::class,
        ClipText::class,
        Quiz::class,
        Answer::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao
}
