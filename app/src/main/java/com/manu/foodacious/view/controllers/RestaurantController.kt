package com.manu.foodacious.view.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.manu.foodacious.model.Restaurant.RestaurantEntity
import com.manu.foodacious.view.models.restaurantItem

class RestaurantController(private val callback : IResControllerCallback) : TypedEpoxyController<List<RestaurantEntity>>() {
    interface IResControllerCallback{
        fun onRestaurantClicked(restaurant : RestaurantEntity)
    }

    override fun buildModels(data: List<RestaurantEntity>?) {
        data?.forEach { restaurant ->
            restaurantItem {
                id(restaurant.restaurantId)
                name(restaurant.restaurantName)
                imageUrl(restaurant.restaurantThumbnail)
                cusine(restaurant.restaurantCusine)
                clickListener{ _, _, _, _ ->
                    callback.onRestaurantClicked(restaurant)
                }
            }
        }
    }


}