package com.aya.chocolateteam.ui.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.aya.chocolateteam.ui.activities.SearchResultActivity
import com.aya.chocolateteam.util.Constants
import kotlin.math.log


class FragmentC : BaseFragment<FragmentCBinding>() {

    override val LOG_TAG: String="FRAGMENT_C"
    override val bindingInflater: (LayoutInflater) -> FragmentCBinding = FragmentCBinding::inflate
    override fun setup() {
        setVisibility(true)
    }
    override fun addCallBack() {
        binding!!.apply {
//            searchBtn.setOnClickListener {
//                search()
//            }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String)=search()
            override fun onQueryTextChange(newText: String?)= false
        })
    }
    }
    private fun search():Boolean{
        binding?.apply {
            val country=DataManager.getCountryByName(searchView.query.toString())
            if (country!=null) {
                val intent=Intent(activity,SearchResultActivity::class.java)
                intent.putExtra(Constants.COUNTRY,country)
                startActivity(intent)
                setVisibility(true)
            }else setVisibility(false)
        }
        return false
    }
    private fun setVisibility(b:Boolean){
        val searchVisible:Int;
        val errorVisible:Int
        if(b) {
            searchVisible = View.VISIBLE
            errorVisible=View.INVISIBLE
        }else
        {
            searchVisible = View.INVISIBLE
            errorVisible=View.VISIBLE
        }
        binding?.apply {
            notFound.visibility=errorVisible
            searchPhoto.visibility=searchVisible
            subtext.visibility=searchVisible
            text.visibility=searchVisible
            errorText.visibility=errorVisible
        }
    }
}