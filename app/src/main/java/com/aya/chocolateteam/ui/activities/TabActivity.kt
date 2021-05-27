package com.aya.chocolateteam.ui.activities

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.aya.chocolateteam.R
import com.aya.chocolateteam.ui.fragments.FragmentA
import com.aya.chocolateteam.ui.fragments.FragmentC
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.ActivityTabBinding
import com.aya.chocolateteam.ui.adapters.ViewpagerAdapter
import com.aya.chocolateteam.ui.fragments.FragmentB
import com.aya.chocolateteam.util.CsvParser
import kotlinx.android.synthetic.main.activity_tab.*
import java.io.BufferedReader
import java.io.InputStreamReader


class TabActivity : BaseActivity<ActivityTabBinding>() {


    override val LOG_TAG: String = "MAIN_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityTabBinding =
        ActivityTabBinding::inflate

    override fun setup() {
        parseFile()
        //setTabs()
        replaceFragment(FragmentA())
    }




    override fun addCallBack() {
        binding?.bottomNavigationView?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.pageHome -> replaceFragment(FragmentA())
                R.id.pageSearch -> replaceFragment(FragmentC())
                R.id.PageMap -> replaceFragment(FragmentB())
            }
            true
        }
    }



    // parse csv file an put info in city list and country list
    private fun parseFile() {
        //Read CSV file
        val inputStream = assets.open("worldcities.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
           DataManager.addCity(parser.parse(it))
        }
        DataManager.createCountriesInfo()
    }
     //add home && search fragment and set tabs text
//    private fun setTabs(){
//        val adapter = Viewpager2Adapter(supportFragmentManager).apply {
//            addFragment(FragmentA(), "Home")
//            addFragment(FragmentC(), "Search")
//        }
//        //set adapter of view pager and title tabs
//        binding?.viewpager?.adapter = adapter
//        binding?.bottomNavigationView?.apply {
//            setupWithViewPager2(binding!!.viewpager)
//            getTabAt(0)?.text = adapter.getPageTitle(0)
//            getTabAt(2)?.text = adapter.getPageTitle(1)
//        }
//
//
//    }

    override fun bindLayout(country: Country) {

    }


    private fun replaceFragment(fragment: Fragment){
        if (fragment !=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }

    }


}