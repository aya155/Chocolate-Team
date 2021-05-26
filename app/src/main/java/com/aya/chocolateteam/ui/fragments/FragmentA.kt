package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentABinding
import com.aya.chocolateteam.util.Constants

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType

class FragmentA : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String="FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding=FragmentABinding::inflate

    override fun setup() {
        // get  initial country
        val country= DataManager.getCurrentCountry()
        bindLayout(country)
    }

    override fun addCallBack() {
        binding?.apply {
            // move to previous country
            beforeBtn.setOnClickListener {
                bindLayout(DataManager.getPreviousCountry())
            }
            //move to next country
            nextBtn.setOnClickListener {
                bindLayout(DataManager.getNextCountry())
            }
        }
    }
    // set layout with information of current country
    override fun bindLayout(country: Country){
        binding?.apply {
            countryName.text=country.name
            populationCitiesChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,seriesArray = makeSeriesArray(country.cities.shuffled().filter { it.population!=0.0 }.take(3)).toTypedArray()))
            description.text = "Population  : ${DataManager.getTotalCountryPopulation(country)} \nISO2  : ${DataManager.getIso2ByCountry(country)}     \nISO3  : ${DataManager.getIso3ByCountry(country)}   \n"
            listItem.adapter= ArrayAdapter(context!!, android.R.layout.simple_list_item_1,DataManager.getCountryCitiesName(country))
        }
    }
}