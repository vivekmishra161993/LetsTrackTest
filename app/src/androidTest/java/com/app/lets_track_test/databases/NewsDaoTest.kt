package com.app.lets_track_test.databases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.app.lets_track_test.getOrAwaitValueTest
import com.app.lets_track_test.news.models.ArticlesItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class NewsDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: NewsDao
    private lateinit var database: NewsDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(), NewsDatabase::class.java).allowMainThreadQueries().build()
        dao = database.newsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNewsTesting() = runBlocking {
        val articlesItem = ArticlesItem("Noida", "Vivek", "test.com", "Testing Room Insert", "Room Test", "test.com", "No Content",1)
        dao.insert(articlesItem)
        val list=dao.getNews().getOrAwaitValueTest()
        assertThat(list).contains(articlesItem)
    }

}