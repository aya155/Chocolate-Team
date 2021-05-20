package com.aya.chocolateteam.data.domain

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class Country(
    val name: String = "",
    val cities : ArrayList<City>
): Parcelable {
    override fun describeContents(): Int {
        return  0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }
}
