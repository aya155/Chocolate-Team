package com.aya.chocolateteam.fragments

import android.os.Bundle
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.chocolateteam.R
import androidx.fragment.app.FragmentTransaction
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class FragmentC : BaseFragment<FragmentCBinding>() {

    override val LOG_TAG: String="FRAGMENT_C"
    override val bindingInflater: (LayoutInflater) -> FragmentCBinding = FragmentCBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
        binding!!.apply {
            searchBtn.setOnClickListener {
                val cityList=DataManager.getCitiesByCountry(searchView.query.toString())
                if (cityList.isNotEmpty()) {
                    text.text=cityList[0].countryName
                }
            }
        }
    }
}