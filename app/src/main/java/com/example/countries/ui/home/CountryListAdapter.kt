package com.example.countries.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.databinding.ItemCountriesBinding
import com.example.countries.ui.favorites.IFavoriteItem

class CountryListAdapter(private var listener: IFavoriteItem? = null) :
    RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private var list = emptyList<CountriesData>()

    inner class CountryListViewHolder(val binding: ItemCountriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val binding = ItemCountriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) =
        with(holder.binding) {
            val country = list[position]
            tvCountryName.text = country.name
            var resource = checkFavorite(country.isFavorite)
            changeFavoriteImage(imgFavorite, resource)
            root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(country)
                it.findNavController().navigate(action)
            }
            root.animation = AnimationUtils.loadAnimation(
                root.context,
                R.anim.fade_transition_vertical
            )
            imgFavorite.setOnClickListener {
                list[position].isFavorite = !country.isFavorite
                resource = checkFavorite(country.isFavorite)
                changeFavoriteImage(imgFavorite, resource)
                listener?.favoriteItem(list[position], position)

            }
        }

    private fun checkFavorite(isFavorite: Boolean): Int {
        return when (isFavorite) {
            true -> R.drawable.ic_baseline_favorite_24
            false -> R.drawable.ic_baseline_favorite_border_24
        }
    }

    private fun changeFavoriteImage(favorite: ImageView, resource: Int) {
        favorite.setImageResource(resource)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<CountriesData>) {
        list = newList
        notifyDataSetChanged()
    }

}