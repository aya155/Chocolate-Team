package com.aya.chocolateteam.ui.fragments

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.data.domain.SortBy
import com.aya.chocolateteam.databinding.FragmentABinding

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.google.android.material.chip.Chip

class FragmentA : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String = "FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding = FragmentABinding::inflate

    override fun setup() {
        // get  initial country
        bindLayout(DataManager.getCurrentCountry())

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