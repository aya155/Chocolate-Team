package com.aya.chocolateteam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aya.chocolateteam.fragments.FragmentA
import com.aya.chocolateteam.fragments.FragmentB
import com.aya.chocolateteam.fragments.FragmentC
import com.aya.chocolateteam.fragments.FragmentD

class ExampleStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragments:ArrayList<Fragment> = arrayListOf(
        FragmentA(),
        FragmentB(),
        FragmentC(),
        FragmentD()
    )


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}