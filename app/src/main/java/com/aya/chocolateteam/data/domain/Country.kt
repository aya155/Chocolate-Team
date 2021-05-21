package com.aya.chocolateteam.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name: String = "",
    val cities : ArrayList<City>
): Parcelable