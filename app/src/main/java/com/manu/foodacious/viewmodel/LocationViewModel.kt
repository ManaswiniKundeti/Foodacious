package com.manu.foodacious.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.foodacious.model.geocode.GeocodeLocation
import com.manu.foodacious.repository.GeocodeRepository
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import com.manu.foodacious.viewstate.ViewState
import kotlinx.coroutines.launch

abstract class LocationViewModel(private val geocodeRepository: GeocodeRepository): ViewModel() {

    abstract fun fetchLocationLiveData(): MutableLiveData<ViewState<GeocodeLocation>>

    fun getLocationData(latitude : Double, longitude : Double){
        viewModelScope.launch {
            fetchLocationLiveData().value = Loading
            val location = geocodeRepository.getCityId(latitude,longitude)
            if(location != null){
                fetchLocationLiveData().value = Success(location)
            }else{
                fetchLocationLiveData().value = com.manu.foodacious.viewstate.Error("There was an error fetching cityId")
            }
        }
    }
}