package com.aya.chocolateteam.fragments

import android.view.LayoutInflater
import com.aya.chocolateteam.databinding.FragmentDBinding

class FragmentD: BaseFragment<FragmentDBinding>() {

    override val LOG_TAG: String="FRAGMENT_D"
    override val bindingInflater: (LayoutInflater) -> FragmentDBinding = FragmentDBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
    }
}