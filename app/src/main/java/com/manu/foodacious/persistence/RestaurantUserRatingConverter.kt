package com.manu.foodacious.persistence

import androidx.room.TypeConverter
import com.manu.foodacious.model.Restaurant.RestaurantLocation
import com.manu.foodacious.model.Restaurant.RestaurantUserRating
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class RestaurantUserRatingConverter {

    @TypeConverter
    fun fromString(value: String): RestaurantUserRating? {
        return getMoshiAdapter().fromJson(value)
    }

    @TypeConverter
    fun fromStockProfile(restaurantUserRating: RestaurantUserRating): String {
        return getMoshiAdapter().toJson(restaurantUserRating)
    }

    private fun getMoshiAdapter(): JsonAdapter<RestaurantUserRating> {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(RestaurantUserRating::class.java)
    }
}