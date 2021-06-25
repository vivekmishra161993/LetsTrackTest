package com.app.lets_track_test.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lets_track_test.news.models.ArticlesItem
import com.app.lets_track_test.news.models.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@Suppress("DEPRECATION")
@HiltViewModel
class NewsViewModel  @Inject constructor(private val repo: NewsRepoInterface) : ViewModel() {

    val newsList=repo.getNewsFromDb()
    val newsLiveData=MutableLiveData<Response<NewsResponse>>()

    fun getNews(){
            viewModelScope.launch{
               val response= repo.getNews()
                newsLiveData.postValue(response)
            }

    }

    fun saveNewsToDb(articles:ArticlesItem){
        viewModelScope.launch {
            repo.saveNewsToDb(articles)
        }
    }



}