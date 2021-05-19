package com.aya.chocolateteam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.chocolateteam.R
import androidx.fragment.app.FragmentTransaction
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.databinding.FragmentABinding

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
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