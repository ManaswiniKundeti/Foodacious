package com.manu.foodacious.view.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.view.models.collectionItem

class CollectionController(private val callback: IControllerCallback): TypedEpoxyController<List<CollectionEntity>>() {
    interface IControllerCallback {
        fun onCollectionClicked(collection: CollectionEntity)
    }

    override fun buildModels(data: List<CollectionEntity>?) {
        data?.forEach { collection ->
            collectionItem {
                id(collection.collectionId)
                imageUrl(collection.imageUrl)
                title(collection.title)
                placeCount(collection.placesCount.toString())
                clickListener { _, _, _, _ ->
                    callback.onCollectionClicked(collection)
                }
            }
        }
    }
}




