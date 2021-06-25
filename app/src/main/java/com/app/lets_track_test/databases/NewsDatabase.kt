package com.app.lets_track_test.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.lets_track_test.news.models.ArticlesItem

@Database(entities = arrayOf(ArticlesItem::class), version = 1, exportSchema = false)
public abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}