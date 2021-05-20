package com.aya.chocolateteam.data.domain

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class City(
    val cityName: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val population: Double
) : Parcelable {
    override fun describeContents(): Int {
        return  0
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }


}
