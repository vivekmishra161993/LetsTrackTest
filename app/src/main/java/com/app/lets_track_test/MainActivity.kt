package com.app.lets_track_test

import android.os.Bundle
import com.app.lets_track_test.news.NewsFragment
import com.app.lets_track_test.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(R.id.container,NewsFragment(),"NewsFragment")
    }

}