package com.manu.foodacious.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manu.foodacious.R
import com.manu.foodacious.extensions.hide
import com.manu.foodacious.extensions.show
import com.manu.foodacious.model.restaurant.RestaurantApiModel
import com.manu.foodacious.model.restaurant.RestaurantEntity
import com.manu.foodacious.view.controllers.RestaurantController
import com.manu.foodacious.viewmodel.RestaurantActivityViewModel
import com.manu.foodacious.viewmodel.RestaurantActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity(), RestaurantController.IRestaurantControllerCallback {

    companion object {
        const val ARG_COLLECTION_ID = "collection_id"
        const val ARG_CITY_ID = "city_id"
        const val ARG_COLLECTION_NAME = "collection_name"
        const val PARAM_CITY = "city"
    }

    private val viewmodelFactory by lazy { RestaurantActivityViewModelFactory(this) }
    private val viewModel by viewModels<RestaurantActivityViewModel> {
        viewmodelFactory
    }
    private var collectionId: Int? = null
    private var cityId: Int? = null
    private var collectionName: String? = null

    private var restaurantList = mutableListOf<RestaurantEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val intent : Intent = intent
        collectionId = intent.getIntExtra(ARG_COLLECTION_ID, -1)
        cityId = intent.getIntExtra(ARG_CITY_ID, 89)
        collectionName = intent.getStringExtra(ARG_COLLECTION_NAME)
        title = collectionName

        if (collectionId == null || collectionId == -1 || cityId == -1) {
            finish()
            return
        }

        val restaurantController = RestaurantController(this).apply {
            spanCount = 1
        }

        val linearLayoutManager = LinearLayoutManager(this)

        restaurant_recycler_view.apply {
            layoutManager = linearLayoutManager
            setController(restaurantController)
        }

        viewModel.restaurantLiveData.observe(this, Observer { viewState ->
            when(viewState){
                is Loading -> {
                    restaurant_progress_bar.show()
                }
                is Success -> {
                    restaurant_progress_bar.hide()
                    restaurantList.clear()
                    restaurantList.addAll(viewState.data!!)

                    restaurantController.setData(restaurantList)
                }
                is Error -> {
                    restaurant_progress_bar.hide()
                    Toast.makeText(this, "ResActivity : Error fetching data from viewmodel", Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getRestaurants(cityId!!, PARAM_CITY, collectionId!!)
    }

    override fun onRestaurantClicked(restaurant: RestaurantEntity) {
        //Toast.makeText(this, "Restaurant clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra(RestaurantDetailActivity.RESTAURANT_ID, restaurant.restaurantId)
        intent.putExtra("restaurant_name", restaurant.restaurantName)
        this.startActivity(intent)
    }
}
