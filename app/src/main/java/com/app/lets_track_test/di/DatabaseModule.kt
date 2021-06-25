package com.app.lets_track_test.di

import android.content.Context
import androidx.room.Room
import com.app.lets_track_test.databases.NewsDao
import com.app.lets_track_test.databases.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: NewsDatabase): NewsDao {
        return appDatabase.newsDao()
    }
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NewsDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }
}