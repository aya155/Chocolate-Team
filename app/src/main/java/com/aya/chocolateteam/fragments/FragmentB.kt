package com.aya.chocolateteam.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.databinding.FragmentBBinding

class FragmentB : BaseFragment<FragmentBBinding>() {
    override val LOG_TAG: String="FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentBBinding=FragmentBBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
    }
}