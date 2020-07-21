package com.starcodex.olimpia.ui.stepform.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.model.LatLng
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stepgeo.*
import kotlinx.android.synthetic.main.fragment_stepgeo.buttonNext
import kotlinx.android.synthetic.main.fragment_stepgeo.buttonPrevious
import javax.inject.Inject


class StepGeoFragment() : Fragment(), OnMapReadyCallback  {

    private val PERMISSION_REQUEST_CODE = 2234
    private val GPS_PROVIDER_CODE = 9823
    private lateinit var currentLocation : Location

    @Inject
    lateinit var pagerControl: ActivityPagerControl

    var googleMap: GoogleMap? = null
    lateinit var fusedLocationClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_stepgeo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapLocation.onCreate(savedInstanceState)
        mapLocation.onResume()
        mapLocation.getMapAsync(this)

        buttonNext.setOnClickListener {
            pagerControl.updateCurrentLocation(LatLng(currentLocation.latitude, currentLocation.longitude))
            pagerControl.nextPage()
        }
        buttonPrevious.setOnClickListener { pagerControl.previouspage() }
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0
        checkPermissions()
    }

    fun checkPermissions(){
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (!hasPermissions(requireActivity().applicationContext, PERMISSIONS)) {
            ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE)
        }else{
            requestLocation()
        }
    }


    fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    requestLocation()
                } else {
                    Toast.makeText(activity,"Permissions needed in order to upload picture", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocation(){
        googleMap!!.isMyLocationEnabled = true
        googleMap!!.uiSettings.isMyLocationButtonEnabled = true
        googleMap!!.setOnMyLocationClickListener(object : GoogleMap.OnMyLocationClickListener{
            override fun onMyLocationClick(p0: Location) {
                currentLocation = p0
                buttonNext.isEnabled = true
            }
        })

        googleMap!!.setOnMyLocationButtonClickListener(object : GoogleMap.OnMyLocationButtonClickListener{
            override fun onMyLocationButtonClick(): Boolean {
                if (!isLocationEnabled(requireContext())) {
                    showLocationIsDisabledAlert()
                }else{
                    getCurrentLocation()
                }
                return false
            }
        })
        getCurrentLocation()
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(){
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let { it: Location ->
                currentLocation = it
                if(googleMap!=null){
                    googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 30f))
                    buttonNext.isEnabled = true
                }
            } ?: kotlin.run {
                // Handle Null case or Request periodic location update https://developer.android.com/training/location/receive-location-updates
            }
        }
    }

    private fun showLocationIsDisabledAlert() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("GPS deactivated")
        .setMessage("Please turn on the GPS in order to access you location")
        .setPositiveButton("Cancel") { dialog, which ->

        }.setNegativeButton("Accept") { dialog, which ->
                startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_PROVIDER_CODE)
        }.show()
    }

    private fun isLocationEnabled(mContext: Context): Boolean {
        val lm = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GPS_PROVIDER_CODE) {
            getCurrentLocation()
        }
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }

}