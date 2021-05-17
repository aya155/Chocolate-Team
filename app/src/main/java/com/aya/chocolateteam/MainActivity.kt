package com.aya.chocolateteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager2()
        initViewPager2WithFragments()
    }

    private fun initViewPager2() {
        var viewPager: ViewPager2 = findViewById(R.id.viewpager)
        var adapter = ViewPagerViewAdapter()
        viewPager.adapter = adapter
        adapter.setNewUsers(createExampleUserList())

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text = createExampleUserList()[position].name
        }.attach()
    }

    private fun initViewPager2WithFragments() {
        var viewPager2:ViewPager2 = findViewById(R.id.viewpager)
        var adapter = ExampleStateAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter
        var tablayout:TabLayout = findViewById(R.id.tablayout)
        var names:Array<String> = arrayOf("Home","Map","Search","MyProfile")
        TabLayoutMediator(tablayout,viewPager2){tab, position ->
            tab.text = names[position]
        }.attach()

    }
    }


    private fun createExampleUserList():ArrayList<User>
    {
        var export:ArrayList<User> = ArrayList()
        return export
    }

