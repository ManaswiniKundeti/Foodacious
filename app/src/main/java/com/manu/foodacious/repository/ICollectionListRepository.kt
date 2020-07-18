package com.manu.foodacious.repository

import com.manu.foodacious.model.collection.CollectionEntity

interface ICollectionListRepository {

    suspend fun getCollectionList(cityId : Int) : List<CollectionEntity>?
}