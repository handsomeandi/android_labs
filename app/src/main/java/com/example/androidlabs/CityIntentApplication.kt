package com.example.androidlabs

import android.app.Application

class CityIntentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CityRepository.initialize(this)
    }
}