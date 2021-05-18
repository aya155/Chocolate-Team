package com.aya.chocolateteam.fragments

import android.view.LayoutInflater
import androidx.fragment.app.FragmentTransaction
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.databinding.FragmentABinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

@Suppress("UNREACHABLE_CODE")
class FragmentA : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String="FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding=FragmentABinding::inflate
    override fun setup() {
        DataManager.getCountariesInfo()
        val country=DataManager.countryList[(0..DataManager.countryList.lastIndex).random()]
        binding?.apply {
            countryName.text=country.name
            populationCitiesChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,seriesArray = makeSeriesArray(country.cities).toTypedArray()))
        }
    }

    override fun addCallBack() {
    }

}




//val aaChartModel1 : AAChartModel = AAChartModel()
//        .chartType(AAChartType.Line)
//        .title("Population")
//        .subtitle("According Longitude")
//        .backgroundColor("#CDCACA")
//        .dataLabelsEnabled(true)
//        .series(arrayOf(
//                AASeriesElement()
//                        .name("Tokyo")
//                        .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6)),
//                AASeriesElement()
//                        .name("NewYork")
//                        .data(arrayOf(0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5)),
//                AASeriesElement()
//                        .name("London")
//                        .data(arrayOf(0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0)),
//                AASeriesElement()
//                        .name("Berlin")
//                        .data(arrayOf(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8))))
//binding?.AAChartView?.aa_drawChartWithChartModel(aaChartModel1)
//
//val aaChartModel2 : AAChartModel = AAChartModel()
//        .chartType(AAChartType.Line)
//        .title("Population")
//        .subtitle("According Latitude")
//        .backgroundColor("#CDCACA")
//        .dataLabelsEnabled(true)
//        .series(arrayOf(
//                AASeriesElement()
//                        .name("Tokyo")
//                        .data(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6)),
//                AASeriesElement()
//                        .name("NewYork")
//                        .data(arrayOf(0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5)),
//                AASeriesElement()
//                        .name("London")
//                        .data(arrayOf(0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0)),
//                AASeriesElement()
//                        .name("Berlin")
//                        .data(arrayOf(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8))))
//binding?.AAChartView2?.aa_drawChartWithChartModel(aaChartModel2)
