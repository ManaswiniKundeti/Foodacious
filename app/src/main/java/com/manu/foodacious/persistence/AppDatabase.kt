package com.manu.foodacious.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manu.foodacious.model.CollectionEntity

@Database(entities = [CollectionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

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