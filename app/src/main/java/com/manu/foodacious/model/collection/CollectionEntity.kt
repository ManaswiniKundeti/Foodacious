package com.manu.foodacious.model.collection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["collectionId","cityId"])
data class CollectionEntity (
    val collectionId: Int,
    val placesCount: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val cityId: Int
)