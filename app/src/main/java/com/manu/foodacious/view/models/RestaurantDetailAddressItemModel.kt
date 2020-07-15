package com.manu.foodacious.view.models

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.manu.foodacious.R

@EpoxyModelClass(layout = R.layout.item_restaurant_detail_address)
abstract class RestaurantDetailAddressItemModel : EpoxyModelWithHolder<RestaurantDetailAddressItemHolder>() {

    @EpoxyAttribute
    lateinit var address : String
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var copyLocationClickListener : View.OnClickListener? = null
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var getDirectionsClickListener : View.OnClickListener? = null


    override fun bind(holder: RestaurantDetailAddressItemHolder) {
        holder.restaurantDetailAddressTextView.text = address

        copyLocationClickListener.let {
            holder.restaurantDetailCopyLocationButton.setOnClickListener(it)
        }

        getDirectionsClickListener.let {
            holder.restaurantDetailGetDirectionsButton.setOnClickListener(it)
        }
    }
}

class RestaurantDetailAddressItemHolder : KotlinHolder(){
    val restaurantDetailAddressTextView by bind<TextView>(R.id.restaurant_details_address_textView)
    val restaurantDetailCopyLocationButton by bind<TextView>(R.id.restaurant_details_copy_location_button)
    val restaurantDetailGetDirectionsButton by bind<TextView>(R.id.restaurant_details_get_directions_button)
}