package com.manu.foodacious.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.manu.foodacious.model.Collection

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collections : List<Collection>)

}