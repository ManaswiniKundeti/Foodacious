package com.manu.foodacious.model.restaurant

import com.squareup.moshi.Json

data class Restaurant(@field:Json(name = "restaurant")val restaurantApiModel : RestaurantApiModel )