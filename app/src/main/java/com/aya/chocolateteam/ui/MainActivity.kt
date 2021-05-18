package com.aya.chocolateteam.ui

import android.view.LayoutInflater
import androidx.fragment.app.FragmentTransaction
import androidx.transition.FragmentTransitionSupport
import com.aya.chocolateteam.fragments.FragmentA
import com.aya.chocolateteam.fragments.FragmentB
import com.aya.chocolateteam.fragments.FragmentC
import com.aya.chocolateteam.fragments.FragmentD

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
        setTabs()

    }


    override fun addCallBack() {
        binding?.apply {

        }
//        log(DataManager.sortByPopulation(SortType.Ascending)[0])
//        log(DataManager.sortByPopulation(SortType.Descending,3))
//        log(DataManager.getCitiesByCountry("iraq"))
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

    private fun setTabs(){
        val adapter = ViewpagerAdapter2(supportFragmentManager).apply {
            addFragment(FragmentA(), "Home")
            addFragment(FragmentB(), "Map")
            addFragment(FragmentC(), "Search")
            addFragment(FragmentD(), "MyProfile")
        }
        binding!!.viewpager.adapter = adapter
        binding!!.tablayout.apply {
            setupWithViewPager(binding!!.viewpager)
            getTabAt(0)?.text = adapter.getPageTitle(0)
            getTabAt(1)?.text = adapter.getPageTitle(1)
            getTabAt(2)?.text = adapter.getPageTitle(2)
            getTabAt(3)?.text = adapter.getPageTitle(3)
        }
    }
//            private fun initViewPager2() {
//        val adapter = ViewPagerViewAdapter()
//        binding?.apply {
//            viewpager.adapter = adapter
//            adapter.setNewUsers(createExampleUserList())
//
//            TabLayoutMediator(tablayout,viewpager){tab, position ->
//                tab.text = createExampleUserList()[position].name
//            }.attach()
//        }
//    }
//
//    private fun initViewPager2WithFragments() {
//        binding?.apply {
//            val adapter = ExampleStateAdapter(supportFragmentManager,lifecycle)
//            viewpager.adapter = adapter
//            val names:Array<String> = arrayOf("Home","Map","Search","MyProfile")
//            TabLayoutMediator(tablayout,viewpager){tab, position ->
//                tab.text = names[position]
//            }.attach()
//        }
//
////        var viewPager2:ViewPager2 = findViewById(R.id.viewpager)
//    }
//
//    private fun createExampleUserList(): ArrayList<User> {
//        return ArrayList()
//    }
}