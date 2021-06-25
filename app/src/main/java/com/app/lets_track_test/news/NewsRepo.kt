package com.app.lets_track_test.news

import androidx.lifecycle.MutableLiveData
import com.app.lets_track_test.databases.NewsDao
import com.app.lets_track_test.network.ApiClient
import com.app.lets_track_test.news.models.ArticlesItem
import javax.inject.Inject

class NewsRepo @Inject constructor(private val newsDao: NewsDao) {

    val newsResponse = MutableLiveData<Pair<Int, ArrayList<ArticlesItem>?>>()
    val dbNewsResponse=MutableLiveData<List<ArticlesItem>>()

    suspend fun getNews() {
        val apiClient = ApiClient.getClient()
        try {
            val response = apiClient.getNews()
            response.let {
                if (it.isSuccessful){
                    newsResponse.postValue(Pair(it.code(),it.body()?.articles))

                }else{
                    newsResponse.postValue(Pair(it.code(),null))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            newsResponse.postValue(Pair(0, null))
        }

    }
    suspend fun  getNewsFromDb(){
        val newsList=newsDao.getNews()
        dbNewsResponse.postValue(newsList)
    }
    suspend fun saveNewsToDb(articles:ArticlesItem){
        articles.let {
            newsDao.insert(articles)
        }
    }
}