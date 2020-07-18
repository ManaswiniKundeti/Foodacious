package com.manu.foodacious.model.collection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollectionEntity (
    @PrimaryKey
    val collectionId: Int,
    val placesCount: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val cityId: Int
)