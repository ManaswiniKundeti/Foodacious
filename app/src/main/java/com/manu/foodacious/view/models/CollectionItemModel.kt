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
import com.manu.foodacious.extensions.changeString
import com.manu.foodacious.extensions.convertCountToString

@EpoxyModelClass(layout = R.layout.item_collection)
abstract class CollectionItemModel : EpoxyModelWithHolder<CollectionItemHolder>(){

    @EpoxyAttribute lateinit var title : String
    @EpoxyAttribute lateinit var placeCount : String
    @EpoxyAttribute lateinit var imageUrl : String
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash) var clickListener : View.OnClickListener? = null


    override fun bind(holder: CollectionItemHolder) {
       holder.collectionTitle.text = title
        holder.collectionPlaceCount.text = placeCount.changeString()
        holder.collectionImageView.load(imageUrl){
            transformations(RoundedCornersTransformation())
        }

        clickListener.let {
            holder.collectionCardView.setOnClickListener(it)
        }
    }

    override fun unbind(holder: CollectionItemHolder) {
        holder.collectionCardView.setOnClickListener(null)
    }
}

class CollectionItemHolder : KotlinHolder(){
    val collectionCardView by bind<CardView>(R.id.collection_item_card_view)
    val collectionImageView by bind<ImageView>(R.id.collection_item_image_view)
    val collectionTitle by bind<TextView>(R.id.collection_item_title_text_view)
    val collectionPlaceCount by bind<TextView>(R.id.collection_item_place_count_text_view)

}