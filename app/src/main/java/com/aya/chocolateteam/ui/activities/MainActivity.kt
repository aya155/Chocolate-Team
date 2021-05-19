package com.aya.chocolateteam.ui.activities

import android.view.LayoutInflater
import com.aya.chocolateteam.ui.fragments.FragmentA
import com.aya.chocolateteam.ui.fragments.FragmentB
import com.aya.chocolateteam.ui.fragments.FragmentC
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.ActivityMainBinding
import com.aya.chocolateteam.ui.adapters.ViewpagerAdapter
import com.aya.chocolateteam.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val LOG_TAG: String = "MAIN_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun setup() {
        parseFile()
        setTabs()

    }


    override fun addCallBack() {
    }


    private fun parseFile() {
        //Read CSV file
        val inputStream = assets.open("worldcities.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
           DataManager.addCity(parser.parse(it))
        }
        DataManager.getCountariesInfo()
    }

    private fun setTabs(){
        val adapter = ViewpagerAdapter(supportFragmentManager).apply {
            addFragment(FragmentA(), "Home")
            addFragment(FragmentB(), "Map")
            addFragment(FragmentC(), "Search")
        }
        binding?.viewpager?.adapter = adapter
        binding?.tablayout?.apply {
            setupWithViewPager(binding!!.viewpager)
            getTabAt(0)?.text = adapter.getPageTitle(0)
            getTabAt(1)?.text = adapter.getPageTitle(1)
            getTabAt(2)?.text = adapter.getPageTitle(2)
        }
    }
}