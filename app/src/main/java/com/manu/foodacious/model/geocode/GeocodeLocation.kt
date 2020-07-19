package com.manu.foodacious.model.geocode


import com.squareup.moshi.Json

data class GeocodeLocation (
    @field:Json(name = "city_id")
    val cityId : Int,
    @field:Json(name = "city_name")
    val cityName : String,
    @field:Json(name = "latitude")
    val latitude : Double,
    @field:Json(name = "longitude")
    val longitude : Double
)