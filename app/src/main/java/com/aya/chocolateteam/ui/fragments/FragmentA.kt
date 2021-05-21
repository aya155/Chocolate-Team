package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentABinding
import com.aya.chocolateteam.util.Constants

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType

class FragmentA : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String="FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding=FragmentABinding::inflate
    override fun setup() {
        DataManager.getCountriesInfo()
        val country= DataManager.getCurrentCountry()
        bindLayout(country)
    }

    override fun addCallBack() {
        binding?.apply {
            beforeBtn.setOnClickListener {
                bindLayout(DataManager.getPreviousCountry())
            }
            nextBtn.setOnClickListener {
                bindLayout(DataManager.getNextCountry())
            }
        }
    }
    private fun bindLayout(country: Country){
        binding?.apply {
            countryName.text=country.name
            populationCitiesChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,seriesArray = makeSeriesArray(country.cities.shuffled().filter { it.population!=0.0 }.take(4)).toTypedArray()))
            description.text="Population  : ${DataManager.getTotalCountryPopulation(country)} \nISO2  : ${DataManager.getIso2ByCountry(country)}     \nISO3  : ${DataManager.getIso3ByCountry(country)}   \n"
        }
    }


}