package com.app.lets_track_test.news

import androidx.lifecycle.LiveData
import com.app.lets_track_test.databases.NewsDao
import com.app.lets_track_test.network.ApiInterface
import com.app.lets_track_test.news.models.ArticlesItem
import com.app.lets_track_test.news.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepo @Inject constructor(private val newsDao: NewsDao, private val api: ApiInterface):NewsRepoInterface {

    override suspend fun getNews():Response<NewsResponse>? {
        return try {
            val response = api.getNews()
            if (response.isSuccessful) {
                response.let {
                    return@let it
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    }
   override fun  getNewsFromDb(): LiveData<List<ArticlesItem>> {
       return newsDao.getNews()
    }
   override suspend fun saveNewsToDb(articles:ArticlesItem){
        articles.let {
            newsDao.insert(it)
        }
    }
}