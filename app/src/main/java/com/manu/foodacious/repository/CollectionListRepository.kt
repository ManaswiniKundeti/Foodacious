package com.manu.foodacious.repository

import android.util.Log
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
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
            val dbCollectionEntityList = collectionDao.getCollectionList(cityId)
            if(dbCollectionEntityList.isNullOrEmpty()){
                val response = foodaciousService.fetchCollectionList(cityId)
                if(response.isSuccessful && response.body() != null){
                    val collectionList = response.body()!!.collections
                    val collectionEntityList = collectionList.map { collection ->
                        val collectionApiModel = collection.collectionApiModel
                        val collectionEntity = CollectionEntity(
                            collectionId = collectionApiModel.collectionId,
                            placesCount = collectionApiModel.placesCount,
                            imageUrl = collectionApiModel.imageUrl,
                            title = collectionApiModel.title,
                            description = collectionApiModel.description,
                            cityId = cityId
                        )
                        collectionDao.insertCollection(collectionEntity)
                        collectionEntity
                    }
                    collectionEntityList
                }else {
                    Log.e(TAG,"There was an error fetching collection list")
                    null
                }
            }else{
                dbCollectionEntityList
            }

        } catch (e : Exception){
            Log.e(TAG,"There was an error fetching collection list", e)
            null
        }
    }
}