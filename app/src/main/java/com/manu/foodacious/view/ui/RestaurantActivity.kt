package com.manu.foodacious.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.manu.foodacious.R
import com.manu.foodacious.extensions.hide
import com.manu.foodacious.extensions.show
import com.manu.foodacious.model.Restaurant.RestaurantEntity
import com.manu.foodacious.view.controllers.RestaurantController
import com.manu.foodacious.viewmodel.MainActivityViewModel
import com.manu.foodacious.viewmodel.MainActivityViewModelFactory
import com.manu.foodacious.viewmodel.RestaurantActivityViewModel
import com.manu.foodacious.viewmodel.RestaurantActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity(), RestaurantController.IRestaurantControllerCallback {

    companion object {
        const val COLLECTION_ID = "collection_id"
    }

    private val viewmodelFactory by lazy { RestaurantActivityViewModelFactory(this) }
    private val viewModel by viewModels<RestaurantActivityViewModel> {
        viewmodelFactory
    }
    private var collectionId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val intent : Intent = intent
        collectionId = intent.getIntExtra(COLLECTION_ID, -1)

        if (collectionId == null || collectionId == -1) {
            finish()
            return
        }

        val restaurantController = RestaurantController(this).apply {
            spanCount = 2
        }

        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = restaurantController.spanSizeLookup

        restaurant_recycler_view.apply {
            layoutManager = gridLayoutManager
            setController(restaurantController)
        }

        viewModel.restaurantLiveData.observe(this, Observer { viewState ->
            when(viewState){
                is Loading -> {
                    restaurant_progress_bar.show()
                }
                is Success -> {
                    restaurant_progress_bar.hide()
                    restaurantController.setData(viewState.data)
                }
                is Error -> {
                    restaurant_progress_bar.hide()
                    Toast.makeText(this, "ResActivity : Error fetching data from viewmodel", Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getRestaurants(89, "city", collectionId!!)
    }

    override fun onRestaurantClicked(restaurant: RestaurantEntity) {
        //Toast.makeText(this, "Restaurant clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra(RestaurantDetailActivity.RESTAURANT_ID, restaurant.restaurantId)
        this.startActivity(intent)
    }
}
