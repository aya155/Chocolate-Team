package com.aya.chocolateteam.util

import com.aya.chocolateteam.data.domain.City

class CsvParser {


    /**
     * this function take a line from csv file and parse it into City object
     * @param line a line from worldcities csv file
     * @return an instance of City with info parsed from the line
     */
    fun parse(line: String): City {
        val tokenList = line.split(",")
        return City(
            cityName = tokenList[Constants.CITY_INDEX],
            countryName = tokenList[Constants.COUNTRY_INDEX],
            iso2 = tokenList[Constants.ISO2_INDEX],
            iso3 = tokenList[Constants.ISO3_INDEX],
            latitude = tokenList[Constants.LATITUDE_INDEX].toDouble(),
            longitude = tokenList[Constants.LONGITUDE_INDEX].toDouble(),
            population = tokenList[Constants.POPULATION_INDEX].toDoubleOrNull() ?: 0.0
        )
    }
}