package com.aya.chocolateteam.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

interface BaseInterface<VB:ViewBinding> {
    val LOG_TAG: String
    val bindingInflater: (LayoutInflater) -> VB
    var _binding: ViewBinding?
    var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()
    fun setup()
     fun addCallBack()
    fun log(value: Any) { Log.v(LOG_TAG, value.toString()) }
}