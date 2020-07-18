package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.network.IFoodaciousService

class GeocodeRepository(private val foodaciousService : IFoodaciousService) : IGeocodeRepository {
    private val TAG = GeocodeRepository::class.java.simpleName

    override suspend fun getCityId(latitude: Double, longitude: Double): Int? {
        return try{
            val response = foodaciousService.fetchCityId(latitude = latitude,longitude = longitude)
            if(response.isSuccessful && response.body() != null){
                response.body()!!.location.cityId
            }else{
                Log.e(TAG, "In Else : Error fetching geocode details")
                null
            }
        }catch (e : Exception){
            Log.e(TAG, "In catch : Error fetching geocode details", e)
            null
        }
    }
}