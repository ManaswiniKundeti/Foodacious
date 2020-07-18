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

class RestaurantDetailActivityViewModel  (
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _restaurantDetailLiveData: MutableLiveData<ViewState<RestaurantEntity>?> = MutableLiveData()
    val restaurantDetailLiveData: LiveData<ViewState<RestaurantEntity>?> = _restaurantDetailLiveData

    fun getRestaurantDetail(restaurantId : Int) {
        viewModelScope.launch {
            _restaurantDetailLiveData.value = Loading
            val restaurantDetail = restaurantRepository.getRestaurantDetail(restaurantId)
            _restaurantDetailLiveData.value = Success(restaurantDetail!!)
        }
    }
}