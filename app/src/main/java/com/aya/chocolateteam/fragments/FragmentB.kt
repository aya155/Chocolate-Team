package com.aya.chocolateteam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.chocolateteam.R
import com.aya.chocolateteam.databinding.FragmentBBinding

class FragmentB : BaseFragment<FragmentBBinding>() {
    override val LOG_TAG: String="FRAGMENT_B"
    override val bindingInflater: (LayoutInflater) -> FragmentBBinding = FragmentBBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
    }

}