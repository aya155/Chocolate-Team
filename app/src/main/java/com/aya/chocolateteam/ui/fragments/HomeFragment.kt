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

import com.google.android.material.chip.Chip

class HomeFragment : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String = "FRAGMENT_A"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding = FragmentABinding::inflate

    override fun setup() {
        // get  initial country
        val country = DataManager.getCurrentCountry()
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
    override fun bindLayout(country: Country) {
        binding?.apply {
            countryName.text = country.name
            val citiesToShow = DataManager.getCountryRandomCities(country,10)
            populationCitiesChart.setBarChart(country)
            showCitiesNames(DataManager.getCitiesName(citiesToShow))
        }
    }

    //To Show cities on chips
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
            binding?.chipGroup?.addView(chip)
        }
    }

    //On Clicking on a Chip
    private val chipClickListener = View.OnClickListener {
        log("CityClicked")
    }
}