package com.manu.foodacious.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.model.geocode.GeocodeLocation
import com.manu.foodacious.repository.CollectionListRepository
import com.manu.foodacious.repository.GeocodeRepository
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import com.manu.foodacious.viewstate.ViewState
import kotlinx.coroutines.launch

class MainActivityViewModel(private val collectionListRepository: CollectionListRepository, geocodeRepository: GeocodeRepository) : LocationViewModel(geocodeRepository) {

    private val _locationLiveData: MutableLiveData<ViewState<GeocodeLocation>> = MutableLiveData()
    val locationLiveData: LiveData<ViewState<GeocodeLocation>> = _locationLiveData

    private val _collectionListLiveData: MutableLiveData<ViewState<List<CollectionEntity>?>> = MutableLiveData()
    val collectionLiveData: LiveData<ViewState<List<CollectionEntity>?>> = _collectionListLiveData

    fun getCollections(cityId : Int){
        viewModelScope.launch {
            _collectionListLiveData.value = Loading
            val collectionList = collectionListRepository.getCollectionList(cityId)
            if(collectionList.isNullOrEmpty()){
                _collectionListLiveData.value = com.manu.foodacious.viewstate.Error("There was an error fetching collection list")
            }else{
                _collectionListLiveData.value = Success(collectionList)
            }
        }
    }

    override fun fetchLocationLiveData(): MutableLiveData<ViewState<GeocodeLocation>> {
        return _locationLiveData
    }
}