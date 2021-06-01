package com.aya.chocolateteam.data

import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortBy
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
     * this function return an instance of City
     * @param index an integer represent index of required city
     * @return an instance of City with info
     */
    fun getCityByIndex(index: Int): City = citesList[index]


    /**
     * this function return a long represent total population of list of cities
     * @param list a list represent cities
     * @return an long represent total population of list of cities
     */
    private fun getTotalPopulation(list: MutableList<City>): Long =
        list.sumOf { it.population }.toLong()



    /**
     * this function return a list of cities name
     * @param cities a list of cities
     * @param sortBy a sort by a property
     * @param sortType a sort type either Ascending or Descending
     * @return a list of cities name
     */
    fun getCitiesName(cities: List<City>, sortBy: SortBy = SortBy.CityName, sortType: SortType = SortType.Descending): List<String> {

        return  when(sortBy)
        {   SortBy.CityName ->   {
                when (sortType) {
                    SortType.Ascending -> getSortedCities(cities,SortBy.CityName,SortType.Ascending).map { it.cityName }
                    else -> getSortedCities(cities).map { it.cityName }
                }
            }

            SortBy.Population-> {
                when (sortType) {
                    SortType.Ascending -> getSortedCities(cities,SortBy.Population,SortType.Ascending).map { it.cityName }
                    else -> getSortedCities(cities,SortBy.Population).map { it.cityName }
                }
            }
                SortBy.Latitude ->
                {
                    when (sortType) {
                        SortType.Ascending -> getSortedCities(cities,SortBy.Latitude,SortType.Ascending).map { it.cityName }
                        else -> getSortedCities(cities,SortBy.Latitude).map { it.cityName }
                    }
                }
            SortBy.Longitude ->
            {
                when (sortType) {
                    SortType.Ascending -> getSortedCities(cities,SortBy.Longitude,SortType.Ascending).map { it.cityName }
                    else -> getSortedCities(cities,SortBy.Longitude).map { it.cityName }
                }
            }
        }
    }


    /**
     * this function return a list of sorted cities
     * @param cities a list of cities
     * @param sortBy a sort by a property
     * @param sortType a sort type either Ascending or Descending
     * @return a list of cities
     */
    private fun getSortedCities(cities: List<City>, sortBy: SortBy = SortBy.CityName, sortType: SortType = SortType.Descending): List<City> {

        return  when(sortBy)
        {   SortBy.CityName ->   {
                when (sortType) {
                    SortType.Ascending -> cities.sortedBy { it.cityName }
                    else -> cities.sortedByDescending{it.cityName }
                }
            }

            SortBy.Population-> {
                when (sortType) {
                    SortType.Ascending -> cities.sortedBy { it.population }
                    else -> cities.sortedByDescending { it.population }
                }
            }
            SortBy.Latitude ->
            {
                when (sortType) {
                    SortType.Ascending -> cities.sortedBy { it.latitude }
                    else -> cities.sortedByDescending { it.latitude }
                }
            }
            SortBy.Longitude ->
            {
                when (sortType) {
                    SortType.Ascending -> cities.sortedBy { it.longitude }
                    else -> cities.sortedByDescending { it.longitude }
                }
            }
        }

//        return cities.sortedBy { it.cityName }.map { it.cityName }
    }



    /**
     * this function return a list of cities name of a country
     * @param country a required country
     * @param sortType sort type
     * @return list of String represent cities of a country
     */
    fun getCountryCities(country: Country, sortBy: SortBy = SortBy.CityName, sortType: SortType = SortType.Descending): List<City> {
        return  getSortedCities(country.cities,sortBy,sortType)
    }


    /**
     * this function take a parameter of string and return a City that satisfy search keyword
     * @param cityName a string represent name of city that user search for
     * @return a City with info satisfy search keyword
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun searchCityByName(cityName: String): City? {
        return citesList.firstOrNull {
            it.cityName.lowercase().trim() == cityName.lowercase().trim()
        }
    }


    /**
     * this function take a parameter of string and return list of cities that satisfy search keyword
     * @param cityName a string represent name of city that user search for
     * @return a list of City with info satisfy search keyword
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun searchCity(cityName: String): List<City> {
        return citesList.filter { it.cityName.lowercase().trim() == cityName.lowercase().trim() }
    }


    fun getCountriesInfo() {
        citesList.groupBy { it.countryName }.entries.map { (name, group) ->
            Country(name, group as ArrayList<City>).let { countryList.add(it) }
        }
    }



    /**
     * this function take a parameter of SortType enum and return list  of city  sorted by population size
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
     * this function take a parameter of SortType enum and return list of city sorted by population size
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
            Country(name, group as ArrayList<City>).let { countryList.add(it) }
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
     * @param countryName a string represent name of required country
     * @return String represent ISO3 of a country
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getIso2ByCountryName(countryName: String): String {
        return countryList.first {
            it.name.lowercase().trim() == countryName.lowercase()
        }.cities[0].iso2
    }

    /**
     * this function return a ISO3 of a country
     * @param countryName a string represent name of required country
     * @return String represent ISO3 of a country
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getIso3ByCountryName(countryName: String): String {
        return countryList.first {
            it.name.lowercase().trim() == countryName.lowercase()
        }.cities[0].iso3
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
     * this function take a parameter of string which represent country name and return list of Cities with info belong to searched country
     * @param countryName a string represent name of country that user search for
     * @return a list of Cities with info belong to searched country
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun getCitiesByCountry(countryName: String): List<City> {
        return citesList.filter {
            it.countryName.lowercase().trim() == countryName.lowercase().trim()
        }
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
     * this function return a list of cities name of a country
     * @param country a required country
     * @param sortType sort type
     * @return list of String represent cities of a country
     */
    fun getCountryCitiesName(country: Country, sortBy: SortBy = SortBy.CityName, sortType: SortType = SortType.Descending): List<String> {
       return  getCitiesName(country.cities,sortBy,sortType)
    }




    /**
     * this function return string represent country latitude and longitude
     * @param country a required country
     * @return list of String represent country latitude and longitude
     */
    fun getCountryLatLan(country: Country): String {
        return "${country.cities[0].latitude.toString()},${country.cities[0].longitude.toString()}"
    }


    /**
     * this function return primary city of a country
     * @param country a required country
     * @return city represent capital of a country
     */
    fun getCapitalCity(country: Country): City? {
        return country.cities.firstOrNull { it.capital.trim() == "primary" }
    }

    /**
     * this function take a list of string
     * @param listToSearch a list of string
     * @return a City with info satisfy search
     */
    @OptIn(ExperimentalStdlibApi::class)
    fun searchCityByLongLat(listToSearch: List<String>): City? {
        return citesList.firstOrNull {
            it.longitude == listToSearch[0].toDouble() && it.latitude == listToSearch[1].toDouble()
        }
    }

    /**
     * this function take a string represent long of city
     * @param long  string
     * @return a City with info satisfy search
     */
    fun searchCityByLong(long: String): City? {
        return citesList.firstOrNull {
            it.longitude == long.trim().toDoubleOrNull()
        }
    }


    /**
     * this function take a string represent lat of city
     * @param lat  string
     * @return a City with info satisfy search
     */
    fun searchCityByLat(lat: String): City? {
        return citesList.firstOrNull {
            it.latitude == lat.trim().toDoubleOrNull()
        }
    }


    /**
     * this function take a string represent population of city
     * @param population  string
     * @return a City with info satisfy search
     */
    fun searchCityByPopulation(population: String): City? {
        return citesList.firstOrNull {
            it.population == population.trim().toDoubleOrNull()
        }
    }

}



