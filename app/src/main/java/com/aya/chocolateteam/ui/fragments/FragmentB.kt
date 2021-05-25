package com.aya.chocolateteam.ui.fragments

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentBBinding
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class FragmentB : BaseFragment<FragmentBBinding>() {
    override val LOG_TAG: String="FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentBBinding= FragmentBBinding::inflate
    private lateinit var mMap: GoogleMap



    val position = LatLng(41.015137, 28.979530)

    var markerOptions = MarkerOptions().position(position)

    lateinit var marker : Marker

    override fun setup() {

        with(binding?.mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            this?.getMapAsync{
                MapsInitializer.initialize(requireActivity().applicationContext)
                setMapLocation(it)
            }
        }
    }

    private fun setMapLocation(map : GoogleMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            setOnMapClickListener {
                if(::marker.isInitialized){
                    marker.remove()
                }
                markerOptions.position(it)
                marker = addMarker(markerOptions)
                Toast.makeText(requireActivity().applicationContext, "Clicked on ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun addCallBack() {
        binding?.apply {

        }
    }

    override fun bindLayout(country: Country) {

    }

}