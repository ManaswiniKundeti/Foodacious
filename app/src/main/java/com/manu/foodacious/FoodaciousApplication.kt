package com.manu.foodacious

import android.app.Application
import com.facebook.stetho.Stetho

class FoodaciousApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}