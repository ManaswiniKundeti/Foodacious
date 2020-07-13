package com.manu.foodacious.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.manu.foodacious.model.Collection.CollectionEntity
import com.manu.foodacious.model.Restaurant.RestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: RestaurantEntity)
}