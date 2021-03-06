package com.aya.chocolateteam.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aya.chocolateteam.R
import com.aya.chocolateteam.data.domain.City
import com.aya.chocolateteam.databinding.ItemCityBinding


class CitiesAdapter(
    var cityList: List<City>,
    val itemClick: (City) -> Unit
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false),
        itemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(cityList[position])
    }

    override fun getItemCount() = cityList.size


    class ViewHolder(view: View, val itemClick: (City) -> Unit) : RecyclerView.ViewHolder(view) {
        val binding = ItemCityBinding.bind(view)
        var cityTxt = this.binding.cityName
        fun bindViewHolder(cityItem: City) {
            cityTxt.text = cityItem.cityName

            //On clicking item
            itemView.setOnClickListener { itemClick(cityItem) }
        }
    }

    //Show data on recyclerview
    fun setData(newList: List<City>) {
        cityList = newList
        notifyDataSetChanged()
    }
}
