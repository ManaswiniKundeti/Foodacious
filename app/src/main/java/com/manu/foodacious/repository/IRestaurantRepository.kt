package com.manu.foodacious.repository

import com.manu.foodacious.model.restaurant.RestaurantEntity

interface IRestaurantRepository {

    suspend fun getRestaurantList(entityId : Int, entityName : String, collectionId : Int)
            : List<RestaurantEntity>?

    suspend fun getRestaurantDetail(restaurantId : Int) : RestaurantEntity?
}