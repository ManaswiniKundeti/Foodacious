package com.manu.foodacious.persistence

import androidx.room.TypeConverter
import com.manu.foodacious.model.restaurant.RestaurantLocation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class RestaurantLocationConverter {

    @TypeConverter
    fun fromString(value: String): RestaurantLocation? {
        return getMoshiAdapter().fromJson(value)
    }

    @TypeConverter
    fun fromRestaurantLocation(restaurantLocation: RestaurantLocation): String {
        return getMoshiAdapter().toJson(restaurantLocation)
    }

    private fun getMoshiAdapter(): JsonAdapter<RestaurantLocation> {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(RestaurantLocation::class.java)
    }
}