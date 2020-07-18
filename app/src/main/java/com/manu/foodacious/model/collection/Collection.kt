package com.manu.foodacious.model.collection

import com.squareup.moshi.Json

data class Collection(@field:Json(name = "collection") val collectionApiModel: CollectionApiModel)