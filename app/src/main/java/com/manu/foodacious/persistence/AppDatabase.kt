package com.manu.foodacious.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.model.restaurant.RestaurantApiModel
import com.manu.foodacious.model.restaurant.RestaurantEntity

@Database(entities = [CollectionEntity::class, RestaurantEntity::class], version = 1)
@TypeConverters(value = [RestaurantLocationConverter::class, RestaurantUserRatingConverter::class, ListConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao() : RestaurantDao
    abstract fun collectionDao() : CollectionDao

    companion object {
        var INSTANCE : AppDatabase? = null

        fun getAppDatabase(context : Context) : AppDatabase {
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"myDB")
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }

}