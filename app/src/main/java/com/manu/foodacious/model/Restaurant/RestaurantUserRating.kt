package com.manu.foodacious.model.Restaurant

import com.squareup.moshi.Json

data class RestaurantUserRating(
    @field:Json(name = "aggregate_rating")
    val aggregate_rating : Float,
    @field:Json(name = "rating_text")
    val rating_text : String,
    @field:Json(name = "rating_color")
    val rating_color : String,
    @field:Json(name = "votes")
    val votes : Int
)