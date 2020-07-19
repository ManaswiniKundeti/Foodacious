package com.manu.foodacious.view.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.manu.foodacious.R
import com.manu.foodacious.extensions.hide
import com.manu.foodacious.extensions.show
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.view.controllers.CollectionController
import com.manu.foodacious.viewmodel.MainActivityViewModel
import com.manu.foodacious.viewmodel.MainActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(),CollectionController.IControllerCallback {

    private val viewmodelFactory by lazy { MainActivityViewModelFactory(this) }
    private val viewModel by viewModels<MainActivityViewModel> {
        viewmodelFactory
    }

    companion object {
        const val CITY_ID = "city_id"
        const val TORONTO_CITY_ID = 89
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var cityId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent : Intent = intent
        cityId = intent.getIntExtra(CITY_ID, TORONTO_CITY_ID)

        if (cityId == null) {
            finish()
            return
        }

        val collectionController = CollectionController(this).apply {
            spanCount = 2
        }

        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = collectionController.spanSizeLookup

        main_recycler_view.apply {
            layoutManager = gridLayoutManager
            setController(collectionController)
        }

        viewModel.cityIdLiveData.observe(this, Observer{ viewState ->
            when(viewState){
                is Loading -> { main_progress_bar.show() }
                is Success -> {
                    main_progress_bar.hide()
                    cityId = viewState.data
                    cityId?.let { viewModel.getCollections(it) }
                }
                is Error -> {
                    main_progress_bar.hide()
                    Toast.makeText(this, "Error fetching cityId", Toast.LENGTH_LONG).show()}
            }
        })

        viewModel.collectionLiveData.observe(this, Observer {viewState ->
            when(viewState){
                is Loading -> { main_progress_bar.show() }
                is Success -> {
                    main_progress_bar.hide()
                    collectionController.setData(viewState.data)
                }
                is Error -> {
                    main_progress_bar.hide()
                    Toast.makeText(this, "Error fetching data", Toast.LENGTH_LONG).show()}
            }
        })
        viewModel.getCollections(cityId!!)
    }

    override fun onCollectionClicked(collection: CollectionEntity) {
        val intent = Intent(this, RestaurantActivity::class.java)
        intent.putExtra(RestaurantActivity.ARG_COLLECTION_ID, collection.collectionId)
        intent.putExtra(RestaurantActivity.ARG_CITY_ID, collection.cityId)
        intent.putExtra(RestaurantActivity.ARG_COLLECTION_NAME, collection.title)
        this.startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_location -> {
               //Fetch current location
                fetchUserLocation()
                true
            }
            else -> true
        }
    }

    private fun fetchUserLocation() {
        Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                viewModel.getCityId(location.latitude, location.longitude)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Security exception", e)
        }
    }
}