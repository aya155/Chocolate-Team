package com.aya.chocolateteam.ui.fragments

import android.content.res.Configuration
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentABinding
import com.aya.chocolateteam.util.Constants

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import kotlinx.android.synthetic.main.fragment_a.*

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

        val string= DataManager.getCitiesByCountry("iraq").map { it.cityName }.take(5)
        val wight=  DataManager.getCitiesByCountry("iraq").map { it.population }.take(5)
        val dataEntry= arrayListOf<DataEntry>().apply {
            string.forEachIndexed { index, s ->
                add(ValueDataEntry(s, wight[index]))
            }
        }

        val pie= AnyChart.column().apply {
            data(dataEntry)
            animation(true)


            when(this@FragmentA.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> background().fill("#353433")
                Configuration.UI_MODE_NIGHT_NO -> background().fill("#c5a880")
            }

        }
        binding?.anyChartView?.setChart(pie)
    }
    // set layout with information of current country
    override fun bindLayout(country: Country){
        binding?.apply {
            countryName.text=country.name
//            populationCitiesChart?.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,backgroundColor="#c5a880",seriesArray = makeSeriesArray(country.cities.shuffled().filter { it.population!=0.0 }.take(3)).toTypedArray()))
//            description.text="Population  : ${DataManager.getTotalCountryPopulation(country)} \nISO2  : ${DataManager.getIso2ByCountry(country)}     \nISO3  : ${DataManager.getIso3ByCountry(country)}   \n"
//            listItem.adapter= ArrayAdapter(Context(), android.R.layout.simple_list_item_1,DataManager.getCountryCitiesName(country))
        }
    }



}