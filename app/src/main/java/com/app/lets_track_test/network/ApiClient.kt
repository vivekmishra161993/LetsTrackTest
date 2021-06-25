package com.app.lets_track_test.network

import com.app.lets_track_test.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    var apiService: ApiInterface? = null

    fun getClient(): ApiInterface {

        apiService = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getNewApiClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface::class.java)

        return apiService!!
    }

    private fun getNewApiClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(180, TimeUnit.SECONDS)
        httpClient.connectTimeout(180, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        return httpClient.build()

    }
}