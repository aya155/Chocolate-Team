package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortBy
import com.aya.chocolateteam.data.domain.SortType
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.aya.chocolateteam.ui.adapters.CustomAdapter
import com.aya.chocolateteam.util.Constants
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import kotlinx.android.synthetic.main.fragment_c.*


class FragmentC : BaseFragment<FragmentCBinding>() {
    override val LOG_TAG: String="FRAGMENT_C"
    lateinit var currentCountry: Country
    private val errorViews= arrayListOf<View?>()
    private val cityViews= arrayListOf<View?>()
    private val countryViews= arrayListOf<View?>()
    private val searchViews= arrayListOf<View?>()
    override val bindingInflater: (LayoutInflater) -> FragmentCBinding = FragmentCBinding::inflate
   //set initial visibility
    override fun setup() {
       initialViews()
       setVisibility(searchViews,errorViews,cityViews,countryViews)
   }
    private fun setVisibility(listVisible :ArrayList<View?>, vararg viewInvisible :ArrayList<View?>){
        viewInvisible.forEach {it.forEach {view-> view?.visibility=View.GONE } }
        listVisible.forEach { it?.visibility=View.VISIBLE}
    }
    private fun initialViews(){
        binding?.run {
            searchViews.apply {
                add(mainText)
                add(subtext)
                add(search_photo)
            }
            errorViews.apply {
                add(errorPhoto)
                add(errorText)
            }
            cityViews.apply {
                add(cardInfo)
                add(photo_city)
                add(cityCountry)
                add(cityName)
                add(iso2)
                add(btnGoToMap)
                add(iso3)
                add(vIso2)
                add(vIso3)
                add(vCityCountry)
                add(vCityPopulation)
                add(cityPopulation)
            }
            countryViews.apply {
                add(cardInfo)
                add(bordChart)
                add(country_chart)
                add(countryCaptail)
                add(vCountryCaptail)
                add(countryPopulation)
                add(vCountryPopulation)
                add(vIso2)
                add(cities)
                add(vIso3)
                add(iso2)
                add(iso3)
            }
        }
    }
    override fun addCallBack() {

        binding!!.apply {
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                //click search icon in keyboard
                override fun onQueryTextSubmit(query: String)=search(search.query.toString())
                //when change text and be empty set visibility
                override fun onQueryTextChange(newText: String?):Boolean{
                    takeIf { search.query.isBlank() }?.let { setVisibility(searchViews,errorViews,cityViews,countryViews) }
                    return false
                }
            })
            cityCountry.setOnClickListener {
                searchCountry((it as TextView).text.toString())
                search.setQuery(it .text.toString(),false)
            }
            countryCaptail.setOnClickListener {
                searchCity((it as TextView).text.toString())
                search.setQuery(it.text.toString(), false)
            }
            spinnerFilter.selected {
                search.queryHint=when(it){
                    0 -> "search city ..... "
                    1 -> "search country ..... "
                    else -> "search long ,lat ..... "
                }
            }
            sortBySpinner.selected {
                listCity.adapter=when(it){
                    0 -> CustomAdapter(DataManager.getCountryCities(country = currentCountry,sortBy = SortBy.CityName,sortType = SortType.Ascending))
                    1 ->CustomAdapter(DataManager.getCountryCities(country = currentCountry,sortBy = SortBy.Population))
                    2 ->CustomAdapter(DataManager.getCountryCities(country = currentCountry,sortBy = SortBy.Latitude))
                    else ->CustomAdapter(DataManager.getCountryCities(country = currentCountry,sortBy = SortBy.Longitude))
                }
            }
            btnGoToMap.setOnClickListener {
                activity?.findViewById<ViewPager>(R.id.viewpager)?.currentItem=0
            }
        }
    }
    private fun search(searchText:String):Boolean{
        binding?.apply {
            when (spinnerFilter.selectedItem.toString()){
                "City" -> searchCity(searchText)
                "Country"-> searchCountry(searchText)
                "Location" -> searchLocation(searchText)
                else ->searchCountry(searchText)
            }
        }
        return false
    }
    private fun errorSearch(){ setVisibility(errorViews,searchViews,countryViews,cityViews) }
    private fun searchCountry(countryName:String) {
            val country= DataManager.getCountryByName(countryName)
            // if country already exiting  in csv file
            if (country!=null) {
                currentCountry=country
                setVisibility(countryViews,searchViews,errorViews,cityViews)
                bindCountryLayout(country)
            }else errorSearch()
    }

    private fun searchLocation(location:String) {

        val city :City?=DataManager.searchCityByLongLat(location.split(','))
        // if city already exiting  in csv file
        if (city!=null) {
            setVisibility(cityViews,searchViews,errorViews,countryViews)
            bindCityLayout(city)
        }else errorSearch()
    }
    private fun searchCity(cityName:String) {
        val city= DataManager.searchCityByName(cityName)
        // if city already exiting  in csv file
        if (city!=null) {
            setVisibility(cityViews,searchViews,errorViews,countryViews)
            bindCityLayout(city)
        }else errorSearch()
    }
    private fun bindCityLayout(city: City) {
        binding?.apply {
            cityPopulation.text= city.population.toString()
            cityCountry.text=city.countryName
            cityName.text=city.cityName
            iso2.text=city.iso2
            iso3.text=city.iso3
            Constants.cityMap=city
        }
    }
    private fun bindCountryLayout(country: Country) {
        binding?.apply {
            countryChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,backgroundColor = "#d2b48c",title = country.name,seriesArray = makeSeriesArray(country.cities.shuffled().filter { it.population!=0.0 }.take(3)).toTypedArray()))
            countryPopulation.text=DataManager.getTotalCountryPopulation(country).toString()
            countryCaptail.text= DataManager.getCapitalCity(country)?.cityName
            iso2.text=DataManager.getIso2ByCountry(country)
            iso3.text=DataManager.getIso3ByCountry(country)
            listCity.adapter=CustomAdapter(DataManager.getCountryCities(country = currentCountry,sortBy = SortBy.CityName,sortType = SortType.Ascending))
        }

    }
    override fun bindLayout(country: Country) {
    }
    private fun Spinner.selected(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
            }
        }
    }
}