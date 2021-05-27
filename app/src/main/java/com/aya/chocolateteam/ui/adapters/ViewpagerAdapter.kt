package com.aya.chocolateteam.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewpagerAdapter(supportFragmentManager: FragmentManager):FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    //list of fragment
    private val mFragmentList=ArrayList<Fragment>()
    //list of tabs
    private val mFragmentTitleList=ArrayList<String>()
    // get fragment by index
    override fun getItem(position: Int)=mFragmentList[position]
    //get number of fragment
    override fun getCount()=mFragmentList.size
    //get title of tab by index
    override fun getPageTitle(position: Int)=mFragmentTitleList[position]
    // add fragment to fragment list and add title of tab to fragment title list
    fun addFragment(fragment: Fragment,title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}