package com.manu.foodacious.repository

interface IGeocodeRepository {
    suspend fun getCityId(latitude : Double, longitude : Double) : Int?
}