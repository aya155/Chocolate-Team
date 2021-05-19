package com.aya.chocolateteam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.chocolateteam.databinding.FragmentDBinding


class FragmentD: BaseFragment<FragmentDBinding>() {

    override val LOG_TAG: String="FRAGMENT_D"
    override val bindingInflater: (LayoutInflater) -> FragmentDBinding = FragmentDBinding::inflate
    override fun setup() {
    }
    override fun addCallBack() {
    }

}