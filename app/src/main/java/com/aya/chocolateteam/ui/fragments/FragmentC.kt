package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.FragmentCBinding

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