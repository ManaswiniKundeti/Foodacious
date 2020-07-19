package com.manu.foodacious.view.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.manu.foodacious.R
import androidx.lifecycle.Observer
import com.manu.foodacious.extensions.hide
import com.manu.foodacious.extensions.show
import com.manu.foodacious.helpers.SharedPreferenceHelper

import com.manu.foodacious.viewmodel.SplashActivityViewModel
import com.manu.foodacious.viewmodel.SplashActivityViewModelFactory
import com.manu.foodacious.viewstate.Error
import com.manu.foodacious.viewstate.Loading
import com.manu.foodacious.viewstate.Success
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val viewmodelFactory by lazy { SplashActivityViewModelFactory(this) }
    private val viewModel by viewModels<SplashActivityViewModel> {
        viewmodelFactory
    }

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
        private const val ARG_CITY_ID = "city_id"
        private val TAG = SplashActivity::class.java.simpleName
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.locationLiveData.observe(this, Observer{ viewState ->
            when(viewState){
                is Loading -> { splash_progress_bar.show() }
                is Success -> {
                    splash_progress_bar.hide()
                    SharedPreferenceHelper(this).clearInt(ARG_CITY_ID)
                    Toast.makeText(this, "Location : ${viewState.data.cityName} ", Toast.LENGTH_SHORT).show()
                    SharedPreferenceHelper(this).putInt(ARG_CITY_ID,viewState.data.cityId)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(MainActivity.CITY_ID, viewState.data.cityId)
                    this.startActivity(intent)
                    finish()
                }
                is Error -> {
                    splash_progress_bar.hide()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(MainActivity.CITY_ID, SharedPreferenceHelper(this).getInt(ARG_CITY_ID,0))
                    this.startActivity(intent)
                    finish()
                    //Toast.makeText(this, "Error fetching cityId TOAST", Toast.LENGTH_LONG).show()
                }
            }
        })

        checkLocationEnabled()
    }

    private fun checkLocationEnabled() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fetchUsersLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this,
                "Grant Foodacious location permission to get recommendations in your city!",
                Toast.LENGTH_LONG).show()
        }

        // Location permission has not been granted yet, request it.
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchUsersLocation()
            } else {
                Toast.makeText(this, "Let us show for toronto", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun fetchUsersLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                viewModel.getLocationData(location.latitude, location.longitude)
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Security exception", e)
        }
    }
}