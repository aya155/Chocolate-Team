package com.aya.chocolateteam.ui.activities

import android.view.LayoutInflater
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.SearchResultBinding
import com.aya.chocolateteam.util.Constants
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType

class SearchResultActivity: BaseActivity<SearchResultBinding>() {
    override val LOG_TAG: String="SEARCH_RESULT"
    override val bindingInflater: (LayoutInflater) -> SearchResultBinding= SearchResultBinding::inflate
    // set layout
    override fun setup() {
        val country=intent.getParcelableExtra<Country>(Constants.COUNTRY)
        bindLayout(country!!)
    }

    override fun addCallBack() {
    }
    // set name of country , build  bar chart , set a total population of this country ,set iso2 and iso3
    override fun bindLayout(country: Country){
        binding?.apply {
            countryName.text=country.name
            populationCitiesChart.aa_drawChartWithChartModel(bindChart(type = AAChartType.Bar,title = country.name,seriesArray = makeSeriesArray(country.cities.shuffled().filter { it.population!=0.0 }.take(4)).toTypedArray()))
            populationCountry.text="Population :${DataManager.getTotalCountryPopulation(country)}"
            iso2Country.text="ISO2 :${DataManager.getIso2ByCountry(country)}"
            iso3Country.text="ISO3 :${DataManager.getIso3ByCountry(country)}"
        }
    }
}