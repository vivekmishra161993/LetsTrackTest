package com.app.lets_track_test.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.lets_track_test.news.models.ArticlesItem
import com.app.lets_track_test.news.models.NewsResponse
import retrofit2.Response

class FakeNewsRepo :NewsRepoInterface {
    private val news= mutableListOf<ArticlesItem>()
    private val newsLiveData=MutableLiveData<List<ArticlesItem>>(news)

    override suspend fun getNews(): Response<NewsResponse> {
        return Response.success(NewsResponse())
    }

    override suspend fun saveNewsToDb(articles: ArticlesItem) {
        news.add(articles)
        refreshNews()
    }

    override fun getNewsFromDb(): LiveData<List<ArticlesItem>> {
        return newsLiveData
    }
    private fun refreshNews(){
        newsLiveData.postValue(news)
    }


}