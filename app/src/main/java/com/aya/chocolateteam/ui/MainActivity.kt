package com.aya.chocolateteam.ui

import android.view.LayoutInflater
import com.aya.chocolateteam.*
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.ActivityMainBinding
import com.aya.chocolateteam.util.CsvParser
import com.google.android.material.tabs.TabLayoutMediator
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
        initViewPager2()
        initViewPager2WithFragments()
        binding?.apply {

        }
//        log(DataManager.sortByPopulation(SortType.Ascending)[0])
//        log(DataManager.sortByPopulation(SortType.Descending,3))
        log(DataManager.getCitiesByCountry("iraq"))
//        log(DataManager.searchCity("Tokyo"))
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

    private fun initViewPager2() {
        val adapter = ViewPagerViewAdapter()
        binding?.apply {
            viewpager.adapter = adapter
            adapter.setNewUsers(createExampleUserList())

            TabLayoutMediator(tablayout,viewpager){tab, position ->
                tab.text = createExampleUserList()[position].name
            }.attach()
        }
    }

    private fun initViewPager2WithFragments() {
        binding?.apply {
            val adapter = ExampleStateAdapter(supportFragmentManager,lifecycle)
            viewpager.adapter = adapter
            val names:Array<String> = arrayOf("Home","Map","Search","MyProfile")
            TabLayoutMediator(tablayout,viewpager){tab, position ->
                tab.text = names[position]
            }.attach()
        }

//        var viewPager2:ViewPager2 = findViewById(R.id.viewpager)
    }

    private fun createExampleUserList(): ArrayList<User> {
        return ArrayList()
    }
}