package com.app.lets_track_test.di

import android.content.Context
import androidx.room.Room
import com.app.lets_track_test.constants.Constants
import com.app.lets_track_test.databases.NewsDao
import com.app.lets_track_test.databases.NewsDatabase
import com.app.lets_track_test.network.ApiInterface
import com.app.lets_track_test.news.NewsRepo
import com.app.lets_track_test.news.NewsRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
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
    @Singleton
    @Provides
    fun injectRetrofitAPI() : ApiInterface {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL).build().create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : NewsDao, api: ApiInterface) = NewsRepo(dao,api) as NewsRepoInterface
}