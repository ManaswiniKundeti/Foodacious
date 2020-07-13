package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.Restaurant.RestaurantEntity
import com.manu.foodacious.network.IFoodaciousService
import com.manu.foodacious.persistence.RestaurantDao
import java.lang.Exception

class RestaurantRepository(
    private val foodaciousService: IFoodaciousService,
    private val restaurantDao: RestaurantDao
) : IRestaurantRepository {

    private val TAG = RestaurantRepository::class.java.simpleName

    override suspend fun getRestaurantList(
        entityId: Int,
        entityName: String,
        collectionId: Int
    ): List<RestaurantEntity>? {
        return try {
            val response =
                foodaciousService.fetchRestaurantsByCollection(entityId, entityName, collectionId)
            if (response.isSuccessful && response.body() != null) {
                val restaurantEntityList = mutableListOf<RestaurantEntity>()
                val restaurantList = response.body()!!.restaurants
                restaurantList.forEach { restaurantItem ->
                    val restaurantEntity = restaurantItem.restaurant
                    restaurantDao.insertRestaurant(restaurantEntity)
                    restaurantEntityList.add(restaurantEntity)
                }
                restaurantEntityList
            } else {
                Log.e(TAG, "There was error fetching restaurants, error in response")
                return null
            }
        } catch (e: Exception) {
            Log.e(TAG, "InCatch : There was error fetching restaurants", e)
            return null
        }
    }
}