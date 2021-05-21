package com.aya.chocolateteam.data

import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortType

object DataManager {
    private val citesList = mutableListOf<City>()
    var countryList = mutableListOf<Country>()

    private var countryIndex = 0
    fun addCity(city: City) {
        citesList.add(city)
    }

    /**
     * this function return a long represent total population of list of cities
     * @param list a list represent cities
     * @return an long represent total population of list of cities
     */
    private fun getTotalPopulation(list: MutableList<City>): Long =
        list.sumOf { it.population }.toLong()
    
    /**
     * this function return data grouped by country
     * @return a List Country with it's name and cities
     */
    fun createCountriesInfo(): List<Country> {
        citesList.groupBy { it.countryName }.entries.map { (name, group) ->
            name.let { Country(it, group as ArrayList<City>) }.let { countryList.add(it) }
        }

        return countryList
    }


    /**
     * this function return an instance of Country
     * @param countryName a string represent name of required country
     * @return an instance of Country with info
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getCountryByName(countryName: String): Country? {
        return countryList.firstOrNull {
            it.name.lowercase().trim() == countryName.lowercase().trim()
        }
    }

    /**
     * this function return an instance of CurrentCountry while swiping between countries info
     * @return an instance of Country with info
     */
    fun getCurrentCountry(): Country = countryList[countryIndex]


    /**
     * this function return an instance of NextCountry while swiping between countries info
     * @return an instance of Country with info
     */
    fun getNextCountry(): Country {
        countryIndex++
        if (countryIndex == countryList.size) {
            countryIndex = 0
        }
        return countryList[countryIndex]
    }


    /**
     * this function return an instance of PreviousCountry while swiping between countries info
     * @return an instance of Country with info
     */
    fun getPreviousCountry(): Country {
        countryIndex--
        if (countryIndex == -1) {
            countryIndex = countryList.size - 1
        }
        return countryList[countryIndex]
    }


    /**
     * this function return an long represent to total population of a given Country
     * @param country required country to get  total population
     * @return a Long represent total number of a country population
     */
    fun getTotalCountryPopulation(country: Country): Long {
        return getTotalPopulation(country.cities)
    }


    /**
     * this function return a ISO2 of a country
     * @param country a string represent name of required country
     * @return String represent ISO3 of a country
     */
    fun getIso2ByCountry(country: Country): String {
        return country.cities[0].iso2
    }

    /**
     * this function return a ISO3 of a country
     * @param country a required country
     * @return String represent ISO3 of a country
     */
    fun getIso3ByCountry(country: Country): String {
        return country.cities[0].iso3
    }

    /**
     * this function return a list of cities name
     * @param cities a list of cities
     * @return a list of cities name
     */
    fun getCitiesName(cities: List<City>): List<String> {
        return cities.map { it.cityName }
    }


    /**
     * this function return a list of cities name of a country
     * @param country a required country
     * @return list of String represent cities of a country
     */
    fun getCountryCitiesName(country: Country): List<String> {
        return getCitiesName(country.cities)
    }

    /**
     * this function return string represent country latitude and longitude
     * @param country a required country
     * @return list of String represent country latitude and longitude
     */
    fun getCountryLatLan(country: Country): String {
        return "${country.cities[0].latitude.toString()},${country.cities[0].longitude.toString()}"
    }
}



