package com.manu.foodacious.repository

import com.manu.foodacious.model.geocode.GeocodeLocation

interface IGeocodeRepository {
    suspend fun getCityId(latitude : Double, longitude : Double) : GeocodeLocation?
}