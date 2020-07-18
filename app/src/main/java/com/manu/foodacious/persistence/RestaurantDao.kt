package com.manu.foodacious.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manu.foodacious.model.restaurant.RestaurantApiModel
import com.manu.foodacious.model.restaurant.RestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: RestaurantEntity)

    @Query("SELECT * FROM RestaurantEntity WHERE collectionId = :collectionId")
    suspend fun getRestaurantList(collectionId : Int) : List<RestaurantEntity>

    @Query("SELECT * FROM RestaurantEntity WHERE restaurantId = :restaurantId")
    suspend fun getRestaurantDetail(restaurantId : Int) : RestaurantEntity

}