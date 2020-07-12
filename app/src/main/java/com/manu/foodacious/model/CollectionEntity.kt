package com.manu.foodacious.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class CollectionEntity (
    @field:Json(name = "collection_id") @PrimaryKey
    val collectionId: Int,
    @field:Json(name = "res_count")
    val placesCount: Int,
    @field:Json(name = "image_url")
    val imageUrl: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String
)