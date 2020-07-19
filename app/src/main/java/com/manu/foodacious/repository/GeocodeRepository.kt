package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.geocode.GeocodeLocation
import com.manu.foodacious.network.IFoodaciousService

class GeocodeRepository(private val foodaciousService : IFoodaciousService) : IGeocodeRepository {
    private val TAG = GeocodeRepository::class.java.simpleName

    override suspend fun getCityId(latitude: Double, longitude: Double): GeocodeLocation? {
        return try{
            val response = foodaciousService.fetchCityId(latitude = latitude,longitude = longitude)
            if(response.isSuccessful && response.body() != null){
                response.body()!!.location
            }else{
                Log.e(TAG, "In Else : Error fetching geocode location")
                null
            }
        }catch (e : Exception){
            Log.e(TAG, "In catch : Error fetching geocode location", e)
            null
        }
    }
}