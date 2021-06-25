package com.app.lets_track_test.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule=MainCoroutineRule()

    @Before
    fun setup(){
        viewModel= NewsViewModel(FakeNewsRepo())

    }
    @Test
    fun `get data from Api`(){
       viewModel.getNews()
        val value=viewModel.newsLiveData
        assertThat(value.value?.code()).isEqualTo(200)

    }
    @Test
    fun `data not null`(){
        viewModel.getNews()
        val value=viewModel.newsLiveData
        assertThat(value.value?.body()?.articles).isNotEqualTo(null)

    }
    @Test
    fun `list not empty`(){
        viewModel.getNews()
        val value=viewModel.newsLiveData
        assertThat(value.value?.body()?.articles?.size).isEqualTo(20)

    }
    @Test
    fun `list empty`(){
        viewModel.getNews()
        val value=viewModel.newsLiveData
        assertThat(value.value?.body()?.articles?.size).isEqualTo(0)

    }

}