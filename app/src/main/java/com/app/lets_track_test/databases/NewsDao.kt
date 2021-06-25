package com.app.lets_track_test.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.lets_track_test.news.models.ArticlesItem

@Dao
interface NewsDao {
    @Query("Select * from news_table")
    suspend fun getNews() : List<ArticlesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsList: List<ArticlesItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: ArticlesItem)
}