package com.aya.chocolateteam.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.databinding.ItemBinding
import com.aya.chocolateteam.databinding.ItemCityBinding


class CustomAdapter(val cityList: List<City>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding= ItemCityBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.cityName.text=cityList[position].cityName
    }
    override fun getItemCount() = cityList.size
}
