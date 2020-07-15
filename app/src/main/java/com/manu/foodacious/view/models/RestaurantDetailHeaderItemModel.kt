package com.manu.foodacious.view.models

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.manu.foodacious.FoodaciousApplication
import com.manu.foodacious.R

@EpoxyModelClass(layout = R.layout.item_restaurant_detail_header)
abstract class RestaurantDetailHeaderItemModel : EpoxyModelWithHolder<RestaurantDetailHeaderItemHolder>() {

    @EpoxyAttribute
    lateinit var thumbImgUrl : String
    @EpoxyAttribute
    var rating : Float? = 0F
    @EpoxyAttribute
    lateinit var name : String
    @EpoxyAttribute
    lateinit var city : String
    @EpoxyAttribute
    lateinit var cost : String
    @EpoxyAttribute
    lateinit var establishment : String
    @EpoxyAttribute
    var cusine : String? = null
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var phoneClickListener : View.OnClickListener? = null


    override fun bind(holder: RestaurantDetailHeaderItemHolder) {
        if(thumbImgUrl.contentEquals("")) {
            holder.restaurantDetailThumImageView.load(R.drawable.placeholder_image)
            {
                transformations(RoundedCornersTransformation())
            }
        }else{
            holder.restaurantDetailThumImageView.load(thumbImgUrl)
            {
                //crossfade(true)
                transformations(RoundedCornersTransformation())
            }
        }
        holder.restaurantDetailRatingBar.rating = rating!!
        holder.restaurantDetailRatingTextView.text = rating.toString()
        holder.restaurantDetailNameTextView.text = name
        holder.restaurantDetailCusineTextView.text = "${establishment} - ${cusine}"
        holder.restaurantDetailCityTextView.text = city
        holder.restaurantDetailCostTextView.text = "Cost for Two - CA$${cost}(approx) without alcohol"
        phoneClickListener.let {
            holder.restaurantDetailPhoneImageButton.setOnClickListener(it)
        }

    }


}

class RestaurantDetailHeaderItemHolder : KotlinHolder(){
    val restaurantDetailThumImageView by bind<ImageView>(R.id.restaurant_detail_thumb_imageView)
    val restaurantDetailRatingBar by bind<RatingBar>(R.id.restaurant_detail_rating_bar)
    val restaurantDetailRatingTextView by bind<TextView>(R.id.restaurant_detail_rating_textView)
    val restaurantDetailNameTextView by bind<TextView>(R.id.restaurant_detail_name_textView)
    val restaurantDetailCusineTextView by bind<TextView>(R.id.restaurant_detail_cusine_textView)
    val restaurantDetailCityTextView by bind<TextView>(R.id.restaurant_detail_city_textView)
    val restaurantDetailPhoneImageButton by bind<ImageButton>(R.id.restaurant_detail_call_imageButton)
    val restaurantDetailCostTextView by bind<TextView>(R.id.restaurant_detail_cost_title_textView)
}