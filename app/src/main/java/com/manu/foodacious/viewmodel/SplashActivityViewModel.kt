package com.manu.foodacious.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.foodacious.model.geocode.GeocodeLocation
import com.manu.foodacious.repository.GeocodeRepository
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import com.manu.foodacious.viewstate.ViewState
import kotlinx.coroutines.launch

class SplashActivityViewModel(geocodeRepository: GeocodeRepository) : LocationViewModel(geocodeRepository) {

    private val _locationLiveData: MutableLiveData<ViewState<GeocodeLocation>> = MutableLiveData()
    val locationLiveData: LiveData<ViewState<GeocodeLocation>> = _locationLiveData

    override fun fetchLocationLiveData(): MutableLiveData<ViewState<GeocodeLocation>> {
        return _locationLiveData
    }

}