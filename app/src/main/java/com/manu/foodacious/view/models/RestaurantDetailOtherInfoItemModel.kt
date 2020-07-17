package com.manu.foodacious.view.models

import android.widget.CheckBox
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.manu.foodacious.R

@EpoxyModelClass(layout = R.layout.item_restaurant_detail_other_info)
abstract class RestaurantDetailOtherInfoItemModel : EpoxyModelWithHolder<RestaurantDetailOtherInfoItemHolder>(){
    @EpoxyAttribute
    lateinit var infoText : String

    override fun bind(holder: RestaurantDetailOtherInfoItemHolder) {
        holder.restaurantDetailInfoTextView.text = infoText
    }
}

class RestaurantDetailOtherInfoItemHolder : KotlinHolder(){
    val restaurantDetailInfoCheckBox by bind<CheckBox>(R.id.info_checkBox)
    val restaurantDetailInfoTextView by bind<TextView>(R.id.info_textView)
}