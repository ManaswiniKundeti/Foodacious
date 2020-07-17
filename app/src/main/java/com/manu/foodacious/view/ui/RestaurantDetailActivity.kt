package com.manu.foodacious.view.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manu.foodacious.R
import com.manu.foodacious.extensions.hide
import com.manu.foodacious.extensions.show
import com.manu.foodacious.model.Restaurant.RestaurantEntity
import com.manu.foodacious.view.controllers.RestaurantDetailController
import com.manu.foodacious.viewmodel.RestaurantDetailActivityViewModel
import com.manu.foodacious.viewmodel.RestaurantDetailActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import java.util.*

class RestaurantDetailActivity  : AppCompatActivity(), RestaurantDetailController.IRestaurantDetailControllerCallback {

    companion object {
        const val RESTAURANT_ID = "restaurant_id"
    }

    private var restaurantId: Int? = null
    private var restaurantName: String? = null

    private val viewmodelFactory by lazy { RestaurantDetailActivityViewModelFactory(this) }
    private val viewModel by viewModels<RestaurantDetailActivityViewModel> {
        viewmodelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        val intent : Intent = intent
        restaurantId = intent.getIntExtra(RestaurantDetailActivity.RESTAURANT_ID, -1)
        restaurantName = intent.getStringExtra("restaurant_name")
        title = restaurantName

        if (restaurantId == null || restaurantId == -1) {
            finish()
            return
        }

        val restaurantDetailController = RestaurantDetailController(this).apply {
            spanCount = 1
        }

//        val gridLayoutManager = GridLayoutManager(this, 1)
//        gridLayoutManager.spanSizeLookup = restaurantDetailController.spanSizeLookup
//
        val linearLayoutManager = LinearLayoutManager(this)

        restaurant_detail_recycler_view.apply {
            layoutManager = linearLayoutManager
            setController(restaurantDetailController)
        }

        viewModel.restaurantDetailLiveData.observe(this,Observer { viewState ->
            when(viewState){
                is Loading -> {
                    restaurant_detail_progress_bar.show()
                }
                is Success -> {
                    restaurant_detail_progress_bar.hide()
                    restaurantDetailController.setData(viewState.data)
                }
                is Error -> {
                    restaurant_detail_progress_bar.hide()
                    Toast.makeText(this, "ResDetailActivity : Error fetching data from viewmodel", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.getRestaurantDetail(restaurantId!!)

    }

    override fun onPhoneClicked(restaurant: RestaurantEntity) {
//        Toast.makeText(this, "Phone Clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${restaurant.restaurantPhoneNumber}")
        this.startActivity(intent)
    }

    override fun onCopyLocationClicked(restaurant: RestaurantEntity) {
//        Toast.makeText(this, "Copy Location CLicked", Toast.LENGTH_SHORT).show()
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("address copied to clipboard",restaurant.restaurantLocation.address )
        clipboard.setPrimaryClip(clip)
    }

    override fun onGetDirectionsClicked(restaurant: RestaurantEntity) {
//        Toast.makeText(this, "Get Directions Clicked", Toast.LENGTH_SHORT).show()
        val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f?z=10",
                restaurant.restaurantLocation.latitude, restaurant.restaurantLocation.longitude)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        this.startActivity(intent)
    }

}