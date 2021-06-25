package com.app.lets_track_test.news

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.lets_track_test.news.models.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
@HiltViewModel
class NewsViewModel  @Inject constructor(application: Application, private val repo: NewsRepo) : AndroidViewModel(application) {

    val newsResponse=repo.newsResponse
    val dbNewsResponse=repo.dbNewsResponse
    private val isNoInternet = MutableLiveData(false)

    fun getNews(){
        if (isOnline()){
            viewModelScope.launch{
                repo.getNews()
            }
        }
    }
    fun getNewsFromDb(){
        viewModelScope.launch {
            repo.getNewsFromDb()
        }
    }
    fun saveNewsToDb(articles:ArticlesItem){
        viewModelScope.launch {
            repo.saveNewsToDb(articles)
        }
    }

     fun isOnline(): Boolean {
        val networkInfo = (getApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        val isInternet = networkInfo != null && networkInfo.isConnected
//        if (!isInternet) {
        isNoInternet.value = !isInternet
//        }
        return isInternet
    }
    private fun getApp(): Application {
        return getApplication()
    }
}