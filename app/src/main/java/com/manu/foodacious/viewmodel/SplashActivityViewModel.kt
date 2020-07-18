package com.manu.foodacious.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.foodacious.repository.GeocodeRepository
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import com.manu.foodacious.viewstate.ViewState
import kotlinx.coroutines.launch

class SplashActivityViewModel(private val geocodeRepository: GeocodeRepository) : ViewModel() {

    private val _cityIdLiveData: MutableLiveData<ViewState<Int>> = MutableLiveData()
    val cityIdLiveData: LiveData<ViewState<Int>> = _cityIdLiveData

    fun getCityId(latitude : Double, longitude : Double){
        viewModelScope.launch {
            _cityIdLiveData.value = Loading
            val cityId = geocodeRepository.getCityId(latitude,longitude)
            if(cityId != null){
                _cityIdLiveData.value = Success(cityId)
            }else{
                _cityIdLiveData.value = com.manu.foodacious.viewstate.Error("There was an error fetching cityId")
            }
        }
    }

}