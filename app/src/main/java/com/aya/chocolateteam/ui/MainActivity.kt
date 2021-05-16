package com.aya.chocolateteam.ui

import android.view.LayoutInflater
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.SortType
import com.aya.chocolateteam.databinding.ActivityMainBinding
import com.aya.chocolateteam.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val LOG_TAG: String = "MAIN_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun setup() {
        parseFile()
    }

    override fun addCallBack() {
        binding?.apply { }
        log(DataManager.sortByPopulation(SortType.Ascending)[0])
        log(DataManager.sortByPopulation(SortType.Descending,3))
        log(DataManager.getCitiesByCountry("Iraq"))
        log(DataManager.searchCity("Tokyo"))
    }


    private fun parseFile() {

        //Read CSV file
        val inputStream = assets.open("worldcities.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
           DataManager.addCity(parser.parse(it))
        }
    }
}