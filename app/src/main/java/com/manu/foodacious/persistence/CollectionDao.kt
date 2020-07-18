package com.manu.foodacious.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.model.restaurant.RestaurantEntity

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CollectionEntity)

    @Query("SELECT * FROM CollectionEntity WHERE cityId = :cityId")
    suspend fun getCollectionList(cityId : Int) : List<CollectionEntity>

}