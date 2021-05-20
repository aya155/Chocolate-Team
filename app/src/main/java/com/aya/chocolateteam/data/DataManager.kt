package com.aya.chocolateteam.data

import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortType

object DataManager {
    private val citesList = mutableListOf<City>()
    var countryList = mutableListOf<Country>()

    private var cityIndex = 0
    private var countryIndex = 0
    fun addCity(city: City) {
        citesList.add(city)
    }

    /**
     * this function return an instance of CurrentCity while swiping between cities info
     * @return an instance of City with info
     */
    fun getCurrentCity(): City = citesList[cityIndex]


    /**
     * this function return an instance of NextCity while swiping between cities info
     * @return an instance of City with info
     */
    fun getNextCity(): City {
        cityIndex++
        if (cityIndex == citesList.size) {
            cityIndex = 0
        }
        return citesList[cityIndex]
    }


    /**
     * this function return an instance of PreviousCity while swiping between cities info
     * @return an instance of City with info
     */
    fun getPreviousCity(): City {
        cityIndex--
        if (cityIndex == -1) {
            cityIndex = citesList.size - 1
        }
        return citesList[cityIndex]
    }

    /**
     * this function take a parameter of string which represent country name and return list of Cities with info belong to searched country
     * @param countryName a string represent name of country that user search for
     * @return a list of Cities with info belong to searched country
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getCitiesByCountry(countryName: String): List<City> {
        return citesList.filter { it.countryName.lowercase().trim() == countryName.lowercase().trim() }
    }


    /**
     * this function return an instance of City
     * @param index an integer represent index of required city
     * @return an instance of City with info
     */
    fun getCityByIndex(index: Int): City = citesList[index]


    /**
     * this function take a parameter of string and return list of cities that satisfy search keyword
     * @param cityName a string represent name of city that user search for
     * @return a list of City with info satisfy search keyword
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun searchCity(cityName: String): List<City> {
        return citesList.filter { it.cityName.lowercase().trim() == cityName.lowercase().trim() }
    }


    /**
     * this function take a parameter of SortType enum and return list sorted by population size
     * @param sortType a type of SortType
     * @return a List of City with info sorted by population
     */
    fun sortByPopulation(sortType: SortType): List<City> {
        return when (sortType) {
            SortType.Ascending -> citesList.sortedBy { it.population }
            else -> citesList.sortedByDescending { it.population }
        }
    }

    /**
     * this function take a parameter of SortType enum and return list sorted by population size
     * @param sortType a type of SortType
     * @param noOfRetrievedCity an integer represent count of required cities to retrieve after sorting
     * @return a List of City with info sorted by population
     */
    fun sortByPopulation(sortType: SortType, noOfRetrievedCity: Int): List<City> {
        return when (sortType) {
            SortType.Ascending -> citesList.sortedBy { it.population }.take(noOfRetrievedCity)
            else -> citesList.sortedByDescending { it.population }.take(noOfRetrievedCity)
        }
    }


    /**
     * this function return data grouped by country
     * @return a List Country with it's name and cities
     */
    fun createCountriesInfo(): List<Country> {
        citesList.groupBy { it.countryName }.entries.map { (name, group) ->
            name?.let { Country(it, group as ArrayList<City>) }?.let { countryList.add(it) }
        }

        return countryList
    }

    /**
     * this function return an instance of Country
     * @param index an integer represent index of required country
     * @return an instance of Country with info
     */
    fun getCountryByIndex(index: Int): Country = countryList[index]

    /**
     * this function return an instance of Country
     * @param countryName a string represent name of required country
     * @return an instance of Country with info
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getCountryByName(countryName: String): Country {
        return countryList.first { it.name.lowercase().trim() == countryName.lowercase().trim() }
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

}

