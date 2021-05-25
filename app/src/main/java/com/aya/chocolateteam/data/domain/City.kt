package com.aya.chocolateteam.data.domain


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val cityName: String,
    val countryName: String,
    val iso2: String,
    val iso3: String,
    val latitude: Double,
    val longitude: Double,
    val capital: String,
    val population: Double
) : Parcelable

//"city","city_ascii","lat","lng","country","iso2","iso3","admin_name","capital","population","id"