package com.example.ilovecats

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ILoveCatsApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}