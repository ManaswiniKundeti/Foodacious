package com.manu.foodacious.view.models

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.manu.foodacious.R

@EpoxyModelClass(layout = R.layout.item_restaurant_detail_details)
abstract class RestaurantDetailDetailsItemModel : EpoxyModelWithHolder<RestaurantDetailDetailsItemHolder>(){

    @EpoxyAttribute
    var cusineData : String? = null
    @EpoxyAttribute
    lateinit var dinnerCost : String

    override fun bind(holder: RestaurantDetailDetailsItemHolder) {
        holder.restaurantDetailCusineDataTextView.text = cusineData
        holder.restaurantDetailDinnerCostTextView.text = "CA$${dinnerCost} for two people(approx.) without alcohol"
    }
}

class RestaurantDetailDetailsItemHolder : KotlinHolder(){

    val restaurantDetailDetailsTitleTextView by bind<TextView>(R.id.restaurant_detail_details_title_textView)
    val resturantDetailCusinesTitleTextView by bind<TextView>(R.id.resturant_detail_cusines_title_textView)
    val restaurantDetailCusineDataTextView by bind<TextView>(R.id.restaurant_detail_cusine_data_textView)
    val restaurantDetailAvgCostTitleTextView by bind<TextView>(R.id.restaurant_detail_avg_cost_title_textView)
    val restaurantDetailDinnerCostTextView by bind<TextView>(R.id.restaurant_detail_dinner_cost_textView)
    val restaurantDetailDinnerCostTitleTextView by bind<TextView>(R.id.restaurant_detail_dinner_cost_title_textView)
    val restaurant_detail_other_info_title_textView by bind<TextView>(R.id.restaurant_detail_other_info_title_textView)
}