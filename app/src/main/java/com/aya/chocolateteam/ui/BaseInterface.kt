package com.aya.chocolateteam.ui

import android.util.Log
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.aya.chocolateteam.data.domain.City
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

interface BaseInterface<VB:ViewBinding> {
    val LOG_TAG: String
    val bindingInflater: (LayoutInflater) -> VB
    var _binding: ViewBinding?
    var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    fun setup()
    fun addCallBack()
    fun log(value: Any) { Log.v(LOG_TAG, value.toString()) }
    fun bindChart(type: AAChartType, title: String, subTitle: String = "", backgroundColor: String = "#CDCACA", seriesArray: Array<AASeriesElement>) = AAChartModel().chartType(type)
            .title(title)
            .subtitle(subTitle)
            .backgroundColor(backgroundColor)
            .dataLabelsEnabled(true)
            .series(seriesArray)

    fun makeSeriesArray(cityList: List<City>) = arrayListOf<AASeriesElement>().apply {
        cityList.forEach { this.add(AASeriesElement().name(it.cityName).data(arrayOf(it.population))) }
    }
}