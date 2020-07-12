package com.manu.foodacious.repository

import com.manu.foodacious.model.Collection

interface ICollectionListRepository {

    suspend fun getCollectionList(cityId : Int) : List<Collection>?
}