package com.aya.chocolateteam.ui.fragments

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import android.view.View
import androidx.core.content.ContextCompat
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortBy
import com.aya.chocolateteam.databinding.FragmentABinding

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import kotlinx.android.synthetic.main.fragment_a.*
import com.google.android.material.chip.Chip

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
    override fun bindLayout(country: Country) {
        binding?.apply {
            countryName.text = country.name
            val citiesToShow = DataManager.getCountryCities(country,SortBy.Population).take(5)
            populationCitiesChart?.aa_drawChartWithChartModel(
                bindChart(
                    type = AAChartType.Bar,
                    title = country.name,
                    seriesArray = makeSeriesArray(
                        citiesToShow
//                        country.cities.shuffled().filter { it.population != 0.0 }.take(5)
                    ).toTypedArray()
                )
            )
            showCitiesNames(DataManager.getCitiesName(citiesToShow))
//            description.text="Population  : ${DataManager.getTotalCountryPopulation(country)} \nISO2  : ${DataManager.getIso2ByCountry(country)}     \nISO3  : ${DataManager.getIso3ByCountry(country)}   \n"
//            listItem.adapter= ArrayAdapter(context!!, android.R.layout.simple_list_item_1,DataManager.getCountryCitiesName(country))
        }
    }

    private fun showCitiesNames(citiesList: List<String>) {

        binding?.chipGroup?.removeAllViews()
        for (index in citiesList.indices) {
            val tagName = citiesList[index]
            val chip = Chip(requireContext())
            val paddingDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2f,
                resources.displayMetrics
            ).toInt()

            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
            chip.text = tagName
//            chip.isCloseIconEnabled= true
//            chip.setCloseIconResource(R.drawable.ic_baseline_close_24)

            chip.isClickable = true
            chip.isFocusable = true
            chip.setOnClickListener(chipClickListener)
            chip.chipBackgroundColor =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.chip_color
                    )
                )
            chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected);
//            //Added click listener on close icon to remove tag from ChipGroup
//            chip.setOnCloseIconClickListener {
//                Log.v("chip","clicked")
//                tagList.remove(tagName)
//                chipGroup.removeView(chip)
//            }

            binding?.chipGroup?.addView(chip)
        }
    }

    private val chipClickListener = View.OnClickListener {

        log("CityClicked")
    }
}