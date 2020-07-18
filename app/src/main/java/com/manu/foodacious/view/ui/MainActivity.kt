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
import com.manu.foodacious.model.collection.CollectionApiModel
import com.manu.foodacious.model.collection.CollectionEntity
import com.manu.foodacious.view.controllers.CollectionController
import com.manu.foodacious.viewmodel.MainActivityViewModel
import com.manu.foodacious.viewmodel.MainActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),CollectionController.IControllerCallback {

    private val viewmodelFactory by lazy { MainActivityViewModelFactory(this) }
    private val viewModel by viewModels<MainActivityViewModel> {
        viewmodelFactory
    }

    companion object {
        const val CITY_ID = "city_id"
        const val TORONTO_CITY_ID = 89
    }
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
}