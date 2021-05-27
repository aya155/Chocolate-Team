package com.aya.chocolateteam.ui.fragments


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City

import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentBBinding
import com.aya.chocolateteam.ui.activities.SearchResultActivity
import com.aya.chocolateteam.util.Constants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_c.*


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

        setVisibility(true)
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

//        binding!!.apply {
//            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                //click search icon in keyboard
//                override fun onQueryTextSubmit(query: String)=search()
//                //when change text and be empty set visibility
//                override fun onQueryTextChange(newText: String?):Boolean{
//                    takeIf { search.query.isBlank() }?.let { setVisibility(true) }
//                    return false
//                }
//            })
//
//            search.queryHint = "Search ..."
//        }
    }

    private fun search():Boolean{
        binding?.apply {
            val country=DataManager.getCountryByName(search.query.toString())
            // if country already exiting  in csv file
            if (country!=null) {
                val intent= Intent(activity, SearchResultActivity::class.java)
                intent.putExtra(Constants.COUNTRY,country)
                startActivity(intent)
                setVisibility(true)
            }// if invalid country
            else setVisibility(false)
        }
        return false
    }

    private fun setVisibility(b:Boolean){
        val searchVisible:Int=if(b) View.VISIBLE else View.INVISIBLE
        val errorVisible:Int=if(b) View.INVISIBLE else View.VISIBLE
        binding?.apply {
            //notFound.visibility=errorVisible
            //searchPhoto.visibility=searchVisible
            //subtext.visibility=searchVisible
           // text.visibility=searchVisible
           // errorText.visibility=errorVisible
        }
    }

    override fun bindLayout(country: Country) {

    }

}