package com.manu.foodacious.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manu.foodacious.network.createFoodaciousService
import com.manu.foodacious.persistence.AppDatabase
import com.manu.foodacious.repository.CollectionListRepository
import com.manu.foodacious.repository.RestaurantRepository
import java.lang.IllegalArgumentException

class RestaurantActivityViewModelFactory (private val context : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RestaurantActivityViewModel::class.java)){
            return RestaurantActivityViewModel(
                RestaurantRepository(
                createFoodaciousService(), AppDatabase.getAppDatabase(context).restaurantDao()
            )
            ) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}