package com.app.lets_track_test.news

import androidx.lifecycle.LiveData
import com.app.lets_track_test.news.models.ArticlesItem
import com.app.lets_track_test.news.models.NewsResponse
import retrofit2.Response

interface NewsRepoInterface {
    suspend fun getNews():Response<NewsResponse>?
    suspend  fun saveNewsToDb(articles: ArticlesItem)
    fun getNewsFromDb():LiveData<List<ArticlesItem>>
}