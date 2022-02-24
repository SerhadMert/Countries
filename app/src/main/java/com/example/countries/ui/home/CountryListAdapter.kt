package com.example.countries.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
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
            val action = country.code?.let { safeCode ->
                country.name?.let { safeName ->
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        safeCode, safeName
                    )
                }
            }
            if (action != null) {
                it.findNavController().navigate(action)
            }
        }
        root.animation = AnimationUtils.loadAnimation(
            root.context,
            R.anim.fade_transition_vertical
        )
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<CountriesData>){
        list = newList
        notifyDataSetChanged()
    }
}