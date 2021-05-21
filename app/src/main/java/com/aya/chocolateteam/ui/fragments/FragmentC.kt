package com.aya.chocolateteam.ui.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.aya.chocolateteam.ui.activities.SearchResultActivity
import com.aya.chocolateteam.util.Constants


class FragmentC : BaseFragment<FragmentCBinding>() {

    override val LOG_TAG: String="FRAGMENT_C"
    override val bindingInflater: (LayoutInflater) -> FragmentCBinding = FragmentCBinding::inflate
    override fun setup() {
        setVisibility(true)
    }
    override fun addCallBack() {
//        binding!!.apply {
//            searchBtn.setOnClickListener {
//                search()
//            }
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String)=search()
//            override fun onQueryTextChange(newText: String?)= false
//        })
//    }
    }
//    private fun search():Boolean{
//        binding?.apply {
//            val listCountry=DataManager.getCountryByName(searchView.query.toString())
//            if (listCountry.isNotEmpty()) {
//                startActivity(Intent(activity,SearchResultActivity::class.java))
//                setVisibility(true)
//            }else setVisibility(false)
//        }
//        return false
//    }
    private fun setVisibility(b:Boolean){
        var searchvisiable:Int;
        var errorVisiable:Int
        if(b) {
            searchvisiable = View.VISIBLE
            errorVisiable=View.INVISIBLE
        }else
        {
            searchvisiable = View.INVISIBLE
            errorVisiable=View.VISIBLE

        }
        binding?.apply {
            notFound.visibility=errorVisiable
            searchPhoto.visibility=searchvisiable
            subtext.visibility=searchvisiable
            text.visibility=searchvisiable
            errorText.visibility=errorVisiable
        }

    }
}