package com.aya.chocolateteam.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.ui.BaseInterface
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

abstract class BaseFragment<VB:ViewBinding>:Fragment(), BaseInterface<VB> {
    abstract override val LOG_TAG: String
    abstract override val bindingInflater: (LayoutInflater) -> VB
    override var _binding: ViewBinding? = null
    override var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()

    abstract override fun setup()
    abstract override fun addCallBack()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return _binding?.root
    }


}