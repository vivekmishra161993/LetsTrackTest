package com.app.lets_track_test.network

import com.app.lets_track_test.news.models.NewsResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("everything?q=keyword&apiKey=f2ab776c6f084852b025a01a5e57c59c")
    suspend fun getNews(): retrofit2.Response<NewsResponse>
}