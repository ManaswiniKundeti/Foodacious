package com.manu.foodacious.model.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class RestaurantEntity(
    @field:Json(name = "id") @PrimaryKey
    val restaurantId: Int,
    @field:Json(name = "name")
    val restaurantName: String,
    @field:Json(name = "url")
    val restaurantUrl: String,
    @field:Json(name = "location")
    val restaurantLocation: RestaurantLocation,
    @field:Json(name = "cuisines")
    val restaurantCusine: String?,
    @field:Json(name = "average_cost_for_two")
    val restaurantCostForTwo: Int,
    @field:Json(name = "thumb")
    val restaurantThumbnail: String,
    @field:Json(name = "user_rating")
    val restaurantUserRating: RestaurantUserRating,
    @field:Json(name = "photos_url")
    val restaurantPhotosUrl: String,
    @field:Json(name = "menu_url")
    val restaurantMenuUrl: String,
    @field:Json(name = "phone_numbers")
    val restaurantPhoneNumber: String,
    @field:Json(name = "highlights")
    val restaurantHighlights: List<String>,
    @field:Json(name = "establishment")
    val restaurantEstablishment: List<String>
)