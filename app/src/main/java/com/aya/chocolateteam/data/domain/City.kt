package com.aya.chocolateteam.data.domain
import com.aya.chocolateteam.util.parseLineToCity

data class City(
    val cityName: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val population: Double
){

    companion object{
        fun fromCSV(line: String): City = parseLineToCity(line)
    }
}