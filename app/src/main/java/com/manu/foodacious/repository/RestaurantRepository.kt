package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.restaurant.RestaurantApiModel
import com.manu.foodacious.model.restaurant.RestaurantEntity
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
                //val restaurantEntityList = mutableListOf<RestaurantApiModel>()
                val restaurantList = response.body()!!.restaurants
                val restaurantEntityList = restaurantList.map { restaurantItem ->
                    val restaurantApiModel = restaurantItem.restaurantApiModel
                    val restaurantEntity = RestaurantEntity(
                        restaurantId = restaurantApiModel.restaurantId,
                        restaurantName = restaurantApiModel.restaurantName,
                        restaurantUrl = restaurantApiModel.restaurantUrl,
                        restaurantLocation = restaurantApiModel.restaurantLocation,
                        restaurantCusine = restaurantApiModel.restaurantCusine,
                        restaurantCostForTwo = restaurantApiModel.restaurantCostForTwo,
                        restaurantThumbnail = restaurantApiModel.restaurantThumbnail,
                        restaurantUserRating = restaurantApiModel.restaurantUserRating,
                        restaurantPhotosUrl = restaurantApiModel.restaurantPhotosUrl,
                        restaurantMenuUrl = restaurantApiModel.restaurantMenuUrl,
                        restaurantPhoneNumber = restaurantApiModel.restaurantPhoneNumber,
                        restaurantHighlights = restaurantApiModel.restaurantHighlights,
                        restaurantEstablishment = restaurantApiModel.restaurantEstablishment,
                        collectionId = collectionId,
                        cityId = entityId
                    )
                    restaurantDao.insertRestaurant(restaurantEntity)
                    restaurantEntity
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

    override suspend fun getRestaurantDetail(restaurantId: Int): RestaurantEntity? {
        return try {
            restaurantDao.getRestaurantDetail(restaurantId)
        } catch (e: Exception) {
            Log.e(TAG, "There was an error fetching favourite stocks", e)
            return null
        }
    }


}