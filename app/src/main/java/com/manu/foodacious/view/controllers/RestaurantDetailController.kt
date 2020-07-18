package com.manu.foodacious.view.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.manu.foodacious.model.restaurant.RestaurantEntity
import com.manu.foodacious.view.models.restaurantDetailAddressItem
import com.manu.foodacious.view.models.restaurantDetailDetailsItem
import com.manu.foodacious.view.models.restaurantDetailHeaderItem
import com.manu.foodacious.view.models.restaurantDetailOtherInfoItem

class RestaurantDetailController(private val callback : RestaurantDetailController.IRestaurantDetailControllerCallback) : TypedEpoxyController<RestaurantEntity>() {
    interface IRestaurantDetailControllerCallback{
        fun onPhoneClicked(restaurant : RestaurantEntity)
        fun onCopyLocationClicked(restaurant : RestaurantEntity)
        fun onGetDirectionsClicked(restaurant: RestaurantEntity)
    }

    override fun buildModels(data: RestaurantEntity?) {
        restaurantDetailHeaderItem {
            if (data != null) {
                id(data.restaurantId)
                thumbImgUrl(data.restaurantThumbnail)
                rating(data.restaurantUserRating.aggregate_rating)
                name(data.restaurantName)
                data.restaurantEstablishment.forEach{
                    establishment(it)
                }
                cusine(data.restaurantCusine)
                city(data.restaurantLocation.city)
                cost(data.restaurantCostForTwo.toString())
                phoneClickListener { _, _, _, _ ->
                    callback.onPhoneClicked(data)
                }
            }
        }

        restaurantDetailAddressItem {
            if (data != null){
                id(data.restaurantId)
                address(data.restaurantLocation.address)
                copyLocationClickListener { _, _, _, _ ->
                    callback.onCopyLocationClicked(restaurant = data)
                }
                getDirectionsClickListener { _, _, _, _ ->
                    callback.onGetDirectionsClicked(data)
                }
            }
        }

        /*data?.restaurantHighlights?.forEach { highlight ->
            model {

            }
        }*/
        restaurantDetailDetailsItem {
            if(data != null){
                id(data.restaurantId)
                cusineData(data.restaurantCusine)
                dinnerCost(data.restaurantCostForTwo.toString())
            }
        }

        data?.restaurantHighlights?.forEach{highlight ->
            restaurantDetailOtherInfoItem {
                if(highlight!= null){
                    id(data.restaurantId)
                    infoText(highlight)
                }
            }
        }

    }

}