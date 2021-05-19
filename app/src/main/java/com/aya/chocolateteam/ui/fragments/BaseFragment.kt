package com.aya.chocolateteam.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.chocolateteam.data.domain.City
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

    protected fun bindChart(type: AAChartType, title: String, subTitle: String = "", backgroundColor: String = "#CDCACA", seriesArray: Array<AASeriesElement>) = AAChartModel().chartType(type)
            .title(title)
            .subtitle(subTitle)
            .backgroundColor(backgroundColor)
            .dataLabelsEnabled(true)
            .series(seriesArray)

    protected fun makeSeriesArray(cityList: List<City>) = arrayListOf<AASeriesElement>().apply {
        cityList.forEach { this.add(AASeriesElement().name(it.cityName).data(arrayOf(it.population))) }
    }
}