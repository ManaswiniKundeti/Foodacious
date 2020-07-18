package com.manu.foodacious.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manu.foodacious.network.createFoodaciousService
import com.manu.foodacious.repository.GeocodeRepository
import java.lang.IllegalArgumentException

class SplashActivityViewModelFactory (private val context : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SplashActivityViewModel::class.java)){
            return SplashActivityViewModel(
                GeocodeRepository(createFoodaciousService())) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}