package com.aya.chocolateteam.ui.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import com.aya.chocolateteam.data.DataManager
import com.aya.chocolateteam.data.domain.Country
import com.aya.chocolateteam.databinding.FragmentCBinding
import com.aya.chocolateteam.ui.activities.SearchResultActivity
import com.aya.chocolateteam.util.Constants

class FragmentC : BaseFragment<FragmentCBinding>() {

    override val LOG_TAG: String="FRAGMENT_C"
    override val bindingInflater: (LayoutInflater) -> FragmentCBinding = FragmentCBinding::inflate
   //set initial visibility
    override fun setup() {
        setVisibility(true)
    }
    override fun addCallBack() {
        binding!!.apply {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //click search icon in keyboard
            override fun onQueryTextSubmit(query: String)=search()
            //when change text and be empty set visibility
            override fun onQueryTextChange(newText: String?):Boolean{
                takeIf { searchView.query.isBlank() }?.let { setVisibility(true) }
                return false
            }
        })

            searchView.queryHint = "Search ..."
        }
    }

    private fun search():Boolean{
        binding?.apply {
            val country=DataManager.getCountryByName(searchView.query.toString())
            // if country already exiting  in csv file
            if (country!=null) {
                val intent=Intent(activity,SearchResultActivity::class.java)
                intent.putExtra(Constants.COUNTRY,country)
                startActivity(intent)
                setVisibility(true)
            }// if invalid country
            else setVisibility(false)
        }
        return false
    }
    private fun setVisibility(b:Boolean){
        val searchVisible:Int=if(b)View.VISIBLE else View.INVISIBLE
        val errorVisible:Int=if(b)View.INVISIBLE else View.VISIBLE
        binding?.apply {
            notFound.visibility=errorVisible
            searchPhoto.visibility=searchVisible
            subtext.visibility=searchVisible
            text.visibility=searchVisible
            errorText.visibility=errorVisible
        }
    }

    override fun bindLayout(country: Country) {

    }
}