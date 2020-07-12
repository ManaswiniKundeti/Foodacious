package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.CollectionEntity
import com.manu.foodacious.network.IFoodaciousService
import com.manu.foodacious.persistence.CollectionDao
import java.lang.Exception

class CollectionListRepository(
    private val foodaciousService : IFoodaciousService,
    private val collectionDao : CollectionDao
) : ICollectionListRepository {

    private val TAG = CollectionListRepository::class.java.simpleName

    override suspend fun getCollectionList(cityId: Int): List<CollectionEntity>? {
        return try{
            val response = foodaciousService.fetchCollectionList(cityId)
            if(response.isSuccessful && response.body() != null){
                val collectionEntityList = mutableListOf<CollectionEntity>()
                val collectionList = response.body()!!.collections
                collectionList.forEach { collection ->
                    val collectionEntity = collection.collection
                    collectionDao.insertCollection(collectionEntity)
                    collectionEntityList.add(collectionEntity)
                }
                collectionEntityList
            }else {
                Log.e(TAG,"There was an error fetching collection list")
                null
            }
        } catch (e : Exception){
            Log.e(TAG,"There was an error fetching collection list", e)
            null
        }
    }
}