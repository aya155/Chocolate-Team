package com.aya.chocolateteam.util

import android.content.res.Resources
import android.widget.TableLayout
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.ui.activities.TabActivity

//"city","city_ascii","lat","lng","country","iso2","iso3","admin_name","capital","population","id"

object Constants {
    const val CITY_INDEX = 1
    const val COUNTRY_INDEX = 4
    const val ISO2_INDEX = 5
    const val ISO3_INDEX = 6
    const val LATITUDE_INDEX = 2
    const val LONGITUDE_INDEX = 3
    const val CAPITAL_INDEX = 8
    const val POPULATION_INDEX = 9
    const val COUNTRY="COUNTRY"
    lateinit var CUSTOM_COLORS:ArrayList<Int>
    var cityMap:City?=null
}