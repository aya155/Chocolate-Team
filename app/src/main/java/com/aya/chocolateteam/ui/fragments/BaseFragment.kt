package com.aya.chocolateteam.ui.fragments

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.ui.BaseInterface
import com.aya.chocolateteam.util.Constants
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface
import com.github.mikephil.charting.interfaces.datasets.IDataSet

abstract class BaseFragment<VB:ViewBinding>:Fragment(), BaseInterface<VB> {
    lateinit var  labels:ArrayList<String>
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

    fun ChartInterface.setBarChart(country: Country){
        val entries = ArrayList<BarEntry>()
        labels= ArrayList()
        DataManager.getCitiesByCountry(country.name).filter { it.population!=0.0 }.shuffled().take(8).forEachIndexed { index, city ->
            entries.add(BarEntry(city.population.toFloat(),index))
            labels.add(city.cityName)
        }
        val barDataSet = BarDataSet(entries, "Cities")
        (this as HorizontalBarChart).data = BarData(labels,barDataSet.apply { colors=Constants.CUSTOM_COLORS })
        setChartStyle(this)
        notifyDataSetChanged()
    }

     fun ChartInterface.setPieChart(country: Country) {
        val entries = ArrayList<Entry>()
         labels= ArrayList()
        DataManager.getCitiesByCountry(country.name).filter { it.population!=0.0 }.shuffled().take(8).forEachIndexed { index, city ->
            entries.add(Entry(city.population.toFloat(),index))
            labels.add(city.cityName)
        }
         val pieDataSet = PieDataSet(entries, "")
         (this as PieChart).data = PieData(labels,pieDataSet.apply { colors=Constants.CUSTOM_COLORS })
         setChartStyle(this)
         notifyDataSetChanged()
    }

    private fun setChartStyle(chartView: Chart<out ChartData<out IDataSet<out Entry>>>?) {
        chartView?.apply {
            animateY(2000)
            setTouchEnabled(true)
            data.setDrawValues(false)
            if(chartView is HorizontalBarChart){
                xAxis.setDrawGridLines(false)
                chartView.setPinchZoom(false)
                chartView.isDoubleTapToZoomEnabled = false
                legend.setCustom(Constants.CUSTOM_COLORS.take(labels.size),labels)
                legend.run {
                    xEntrySpace=5f
                    yEntrySpace = 5f
                    isWordWrapEnabled=true
                }
                xAxis.isEnabled = false
            }else if (chartView is PieChart){
                chartView.setDrawSliceText(false)
                chartView.setHoleColor(android.R.color.transparent)
            }
            setDescription("")
            data.setValueTextSize(7f)
            legend.apply {
                formSize = 15f
                form = Legend.LegendForm.CIRCLE
                position = Legend.LegendPosition.BELOW_CHART_LEFT
                textSize = 12f
                textColor = ContextCompat.getColor(context,R.color.text_color)
                xEntrySpace = 5f
                yEntrySpace = 5f
                isWordWrapEnabled=true
            }
        }
    }
}