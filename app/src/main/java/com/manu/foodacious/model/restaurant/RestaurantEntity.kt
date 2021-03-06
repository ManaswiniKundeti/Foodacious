package com.manu.foodacious.model.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class RestaurantEntity(
    @PrimaryKey
    val restaurantId: Int,
    val restaurantName: String,
    val restaurantUrl: String,
    val restaurantLocation: RestaurantLocation,
    val restaurantCusine: String?,
    val restaurantCostForTwo: Int,
    val restaurantThumbnail: String,
    val restaurantUserRating: RestaurantUserRating,
    val restaurantPhotosUrl: String,
    val restaurantMenuUrl: String,
    val restaurantPhoneNumber: String,
    val restaurantHighlights: List<String>,
    val restaurantEstablishment: List<String>,
    val collectionId : Int,
    val cityId : Int
)