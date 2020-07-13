package com.manu.foodacious.view.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.manu.foodacious.R

@EpoxyModelClass(layout = R.layout.item_restaurant)
abstract class RestaurantItemModel : EpoxyModelWithHolder<RestaurantItemHolder>(){

    @EpoxyAttribute
    lateinit var name : String
    @EpoxyAttribute
    var cusine : String? = null
    @EpoxyAttribute
    lateinit var imageUrl : String
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash) var clickListener : View.OnClickListener? = null


    override fun bind(holder: RestaurantItemHolder) {
        holder.restaurantNameTextView.text = name
        holder.restaurantCusineTextView.text = cusine
        holder.restaurantUrlImageView.load(imageUrl){
            transformations(RoundedCornersTransformation())
        }

        clickListener.let {
            holder.restaurantCardView.setOnClickListener(it)
        }
    }

    override fun unbind(holder: RestaurantItemHolder) {
        holder.restaurantCardView.setOnClickListener(null)
    }
}

class RestaurantItemHolder : KotlinHolder(){
    val restaurantCardView by bind<CardView>(R.id.restaurant_item_card_view)
    val restaurantUrlImageView by bind<ImageView>(R.id.restaurant_item_url_image_view)
    val restaurantNameTextView by bind<TextView>(R.id.restaurant_item_name_text_view)
    val restaurantCusineTextView by bind<TextView>(R.id.restaurant_item_cusine_text_view)

}