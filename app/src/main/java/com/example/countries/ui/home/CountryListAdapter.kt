package com.example.countries.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.databinding.ItemCountriesBinding

class CountryListAdapter :
    RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private var list = emptyList<CountriesData>()

    inner class CountryListViewHolder(val binding: ItemCountriesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val binding = ItemCountriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) = with(holder.binding) {
        val country = list[position]
        countryName.text = country.name
        root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(country.code)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<CountriesData>){
        list = newList
        notifyDataSetChanged()
    }
}