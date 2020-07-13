package com.manu.foodacious.repository

import com.manu.foodacious.model.Collection.CollectionEntity

interface ICollectionListRepository {

    suspend fun getCollectionList(cityId : Int) : List<CollectionEntity>?
}