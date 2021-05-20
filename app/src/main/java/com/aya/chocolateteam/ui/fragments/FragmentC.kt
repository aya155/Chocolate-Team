package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import androidx.viewpager.widget.ViewPager
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.aya.chocolateteam.util.Constants


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
                    Constants.SEARCH_COUNTRY=searchView.query.toString()
                    activity!!.findViewById<ViewPager>(R.id.viewpager).currentItem=0
                }
            }
        }
    }
}