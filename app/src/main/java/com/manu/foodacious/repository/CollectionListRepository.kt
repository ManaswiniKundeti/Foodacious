package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.Collection
import com.manu.foodacious.network.IFoodaciousService
import com.manu.foodacious.persistence.CollectionDao
import java.lang.Exception

class CollectionListRepository(
    private val foodaciousService : IFoodaciousService,
    private val collectionDao : CollectionDao
) : ICollectionListRepository {

    private val TAG = CollectionListRepository::class.java.simpleName

    override suspend fun getCollectionList(cityId: Int): List<Collection>? {
        return try{
            val response = foodaciousService.fetchCollectionList(89)
            if(response.isSuccessful && response.body() != null){
                val collectionList = response.body()!!
                collectionDao.insertCollection(collectionList)
                collectionList
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