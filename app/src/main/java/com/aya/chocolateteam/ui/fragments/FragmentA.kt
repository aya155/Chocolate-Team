package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.FragmentABinding

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType

@Suppress("UNREACHABLE_CODE")
class FragmentA : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String="FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding=FragmentABinding::inflate

    override fun setup() {
        DataManager.getCountriesInfo()
        val country= DataManager.countryList[(0..DataManager.countryList.lastIndex).random()]
        binding?.apply {
            countryName.text=country.name
            populationCitiesChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,seriesArray = makeSeriesArray(country.cities).toTypedArray()))
        }
    }

    override fun addCallBack() {

    }

}