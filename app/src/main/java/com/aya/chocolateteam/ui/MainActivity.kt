package com.aya.chocolateteam.ui

import android.content.Context
import android.view.LayoutInflater
import com.aya.chocolateteam.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val LOG_TAG: String = "MAIN_ACTIVITY"

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate
    override fun setup() {
        openFile(this)
    }

    override fun callBack() {
        TODO("Not yet implemented")
    }
}


private fun openFile(context : Context) {

    //Read CSV file
    val inputStream = context.assets.open("worldcities.csv")
    val buffer = BufferedReader(InputStreamReader(inputStream))
    buffer.forEachLine {
       // log(it)
    }
}