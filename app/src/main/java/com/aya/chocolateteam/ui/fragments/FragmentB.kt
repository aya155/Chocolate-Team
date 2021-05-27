package com.aya.chocolateteam.ui.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentABinding

class FragmentB : BaseFragment<FragmentABinding>() {
    override val LOG_TAG: String = "FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentABinding = FragmentABinding::inflate
    override fun setup() {
        TODO("Not yet implemented")
    }

    override fun addCallBack() {
        TODO("Not yet implemented")
    }

    override fun bindLayout(country: Country) {
        TODO("Not yet implemented")
    }
}