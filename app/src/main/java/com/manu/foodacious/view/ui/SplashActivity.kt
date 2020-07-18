package com.manu.foodacious.view.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.manu.foodacious.R

class SplashActivity: AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
        private val TAG = SplashActivity::class.java.simpleName
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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
        Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                Log.d(TAG, "Location fetched: ${location.latitude}")
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Security exception", e)
        }
    }
}