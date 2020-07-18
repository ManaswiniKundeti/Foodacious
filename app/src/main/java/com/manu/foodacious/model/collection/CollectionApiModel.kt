package com.manu.foodacious.model.collection

import com.squareup.moshi.Json

data class CollectionApiModel (
    @field:Json(name = "collection_id")
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