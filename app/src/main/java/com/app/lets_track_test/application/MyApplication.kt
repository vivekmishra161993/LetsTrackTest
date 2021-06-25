package com.app.lets_track_test.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    private val TAG = "MyApplication"

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

    }

    override fun onCreate() {
        super.onCreate()


    }

}