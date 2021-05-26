package com.aya.chocolateteam.ui.fragments


import android.view.LayoutInflater
import android.widget.Toast
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City

import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentBBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_b.*


class FragmentB : BaseFragment<FragmentBBinding>(), OnMapReadyCallback {
    override val LOG_TAG: String = "FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentBBinding = FragmentBBinding::inflate


    var position = LatLng(44.3125881, 31.9956919)
    var markerOptions = MarkerOptions().position(position)
    lateinit var marker: Marker


    private var gMap: GoogleMap? = null

    override fun setup() {
        Toast.makeText(activity, "setup", Toast.LENGTH_SHORT).show()
        log("setup")
        mapInit()
    }

    fun mapInit() {
        with(binding!!.mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            this.getMapAsync(this@FragmentB)
//            this.getMapAsync {
//                MapsInitializer.initialize(requireContext())
//                setMapLocation(it)
//            }

        }
    }


    //make move to address on map
    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        gMap?.setOnMapClickListener {
            if (::marker.isInitialized) {
                marker.remove()
            }
            markerOptions.position(it)
            Toast.makeText(activity, "Clicked on ", Toast.LENGTH_SHORT).show()
            log("Clicked on ")
        }
//        moveMapCamera(DataManager.getCurrentCity())
    }

    private fun setMapLocation(map: GoogleMap) {
        log("setMapLocation")
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            setOnMapClickListener {
                if (::marker.isInitialized) {
                    marker.remove()
                }
                markerOptions.position(it)
                marker = addMarker(markerOptions)!!
                log("Clicked on ")
            }
        }
    }

    private fun addCityToMap(city: City) {
        log("addCityToMap")
        //to avoid null value in csv file
        try {
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(city.latitude.toDouble(), city.longitude.toDouble()))
                .tilt(20f)
                .zoom(10f)
                .bearing(0f)
                .build()
            gMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        }catch (e: NullPointerException){
            Toast.makeText(activity, e.message,Toast.LENGTH_SHORT).show()
            log(e.message.toString())
        }


    }


    override fun addCallBack() {
        binding?.apply {
            button.setOnClickListener {
                position = LatLng(41.015137, 32.979530)
                log("button on ")
                addCityToMap(DataManager.getCurrentCity())
            }
        }
    }

    override fun bindLayout(country: Country) {

    }

}