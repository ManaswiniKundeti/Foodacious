package com.manu.foodacious.model.restaurant

import com.squareup.moshi.Json

data class RestaurantLocation(
    @field:Json(name = "address")
    val address : String,
    @field:Json(name = "locality")
    val locality : String,
    @field:Json(name = "city")
    val city : String,
    @field:Json(name = "city_id")
    val city_id : Int,
    @field:Json(name = "latitude")
    val latitude : Float,
    @field:Json(name = "longitude")
    val longitude : Float,
    @field:Json(name = "zipcode")
    val zipcode : String,
    @field:Json(name = "country_id")
    val country_id : Int,
    @field:Json(name = "locality_verbose")
    val locality_verbose : String
)