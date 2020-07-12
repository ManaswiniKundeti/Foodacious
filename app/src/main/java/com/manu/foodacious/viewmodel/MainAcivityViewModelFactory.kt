package com.manu.foodacious.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manu.foodacious.network.createFoodaciousService
import com.manu.foodacious.persistence.AppDatabase
import com.manu.foodacious.repository.CollectionListRepository
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val context : Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(CollectionListRepository(
                createFoodaciousService(), AppDatabase.getAppDatabase(context).collectionDao()
            )) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}