package ru.popkov.russeliterature.features.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.popkov.russeliterature.features.core.data.local.daos.ClipDao
import ru.popkov.russeliterature.features.core.data.local.daos.FeedDao
import ru.popkov.russeliterature.features.core.data.local.daos.UserDao
import ru.popkov.russeliterature.features.core.data.local.entities.Answer
import ru.popkov.russeliterature.features.core.data.local.entities.Article
import ru.popkov.russeliterature.features.core.data.local.entities.Author
import ru.popkov.russeliterature.features.core.data.local.entities.ClipText
import ru.popkov.russeliterature.features.core.data.local.entities.Clips
import ru.popkov.russeliterature.features.core.data.local.entities.Converters
import ru.popkov.russeliterature.features.core.data.local.entities.Poet
import ru.popkov.russeliterature.features.core.data.local.entities.Quiz
import ru.popkov.russeliterature.features.core.data.local.entities.UserData

@Database(
    entities = [
        UserData::class,
        Author::class,
        Article::class,
        Poet::class,
        Clips::class,
        ClipText::class,
        Quiz::class,
        Answer::class,
    ],
    version = 8,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

    abstract fun clipDao(): ClipDao

    abstract fun userDao(): UserDao
}
