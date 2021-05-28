package com.aya.chocolateteam.ui.fragments


import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentBBinding
import com.aya.chocolateteam.ui.activities.SearchResultActivity
import com.aya.chocolateteam.util.Constants
import com.aya.chocolateteam.util.toFlagEmoji
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_c.*


class FragmentB : BaseFragment<FragmentBBinding>(),OnMapReadyCallback {
    override val LOG_TAG: String = "FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentBBinding = FragmentBBinding::inflate


    var position = LatLng(44.3125881, 31.9956919)
    var markerOptions = MarkerOptions().position(position)
    lateinit var marker: Marker


//    private var gMap: GoogleMap? = null
    private lateinit var gMap: GoogleMap

    override fun setup() {
//        Toast.makeText(activity, "setup", Toast.LENGTH_SHORT).show()
        log("setup")
        mapInit()
        setVisibility(true)
    }

    fun mapInit() {

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

        with(mapFragment) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync(this@FragmentB)
//            this.getMapAsync {
//                MapsInitializer.initialize(requireContext())
//                setMapLocation(it)
//            }

        }
    }


    //make move to address on map
    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        addCityToMap(DataManager.getCurrentCity())
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
        try {

            gMap!!.clear()

            val latLng = LatLng(city.latitude, city.longitude)
            val cameraPosition = CameraPosition.Builder()
                .target(latLng) // Sets the center of the map to Mountain View
                .zoom(17f) // Sets the zoom
                .bearing(90f) // Sets the orientation of the camera to east
                .tilt(30f) // Sets the tilt of the camera to 30 degrees
                .build() // Creates a CameraPosition from the builder

            gMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            gMap!!.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("🏢 ${city.cityName}").snippet("${city.iso2.toFlagEmoji()} ${city.countryName} \n📊 ${city.population} \n ISO2: ${city.iso2} ")
                    .icon( BitmapDescriptorFactory.fromResource(R.drawable.city))
            )

            gMap!!.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoWindow(arg0: Marker): View? {
                    return null
                }

                override fun getInfoContents(marker: Marker): View {
                    val info = LinearLayout(requireContext())
                    info.orientation = LinearLayout.VERTICAL
                    val title = TextView(requireContext())
                    title.setTextColor(Color.BLACK)
                    title.gravity = Gravity.CENTER
                    title.setTypeface(null, Typeface.BOLD)
                    title.text = marker.title
                    val snippet = TextView(requireContext())
                    snippet.setTextColor(Color.GRAY)
                    snippet.text = marker.snippet
                    info.addView(title)
                    info.addView(snippet)
                    return info
                }
            })

        } catch (e: NullPointerException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            log(e.message.toString())
        }
    }


    override fun addCallBack() {
        binding?.apply {
            button.setOnClickListener {
                addCityToMap(DataManager.getNextCity())
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

    private fun search(): Boolean {
        binding?.apply {
            val country = DataManager.getCountryByName(search.query.toString())
            // if country already exiting  in csv file
            if (country != null) {
                val intent = Intent(activity, SearchResultActivity::class.java)
                intent.putExtra(Constants.COUNTRY, country)
                startActivity(intent)
                setVisibility(true)
            }// if invalid country
            else setVisibility(false)
        }
        return false
    }

    private fun setVisibility(b: Boolean) {
        val searchVisible: Int = if (b) View.VISIBLE else View.INVISIBLE
        val errorVisible: Int = if (b) View.INVISIBLE else View.VISIBLE
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