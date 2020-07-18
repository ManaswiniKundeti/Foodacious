package com.manu.foodacious.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.foodacious.model.restaurant.RestaurantEntity
import com.manu.foodacious.repository.RestaurantRepository
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import com.manu.foodacious.viewstate.ViewState
import kotlinx.coroutines.launch

class RestaurantActivityViewModel (private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private val _restaurantLiveData: MutableLiveData<ViewState<List<RestaurantEntity>?>> = MutableLiveData()
    val restaurantLiveData: LiveData<ViewState<List<RestaurantEntity>?>> = _restaurantLiveData


    fun getRestaurants(entityId : Int, entityName : String, collectionId : Int){
        viewModelScope.launch {
            _restaurantLiveData.value = Loading
            val restaurantList = restaurantRepository.getRestaurantList(entityId, entityName, collectionId)
            if(restaurantList.isNullOrEmpty()){
                _restaurantLiveData.value = com.manu.foodacious.viewstate.Error("There was an error fetching restaurants from given collection")
            }else{
                _restaurantLiveData.value = Success(restaurantList)
            }
        }
    }


}