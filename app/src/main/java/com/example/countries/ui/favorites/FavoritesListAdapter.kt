package com.example.countries.ui.favorites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.databinding.ItemCountriesBinding

class FavoritesListAdapter(private var listener: IFavoriteItem? = null) : RecyclerView.Adapter<FavoritesListAdapter.FavoritesViewHolder>() {

    private var favoriteList = emptyList<CountriesData>()

    inner class FavoritesViewHolder(val binding: ItemCountriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding =
            ItemCountriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) =
        with(holder.binding) {
            val favorite = favoriteList[position]
            tvCountryName.text = favorite.name
            imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            root.setOnClickListener {
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                    favorite
                )
                it.findNavController().navigate(action)
            }
            root.animation = AnimationUtils.loadAnimation(
                root.context,
                R.anim.fade_transition_vertical
            )
        }

    override fun getItemCount() = favoriteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<CountriesData>){
        favoriteList = newList
        notifyDataSetChanged()
    }

    fun unFavorite(position: Int) {
        listener?.let {
            listener?.favoriteItem(favoriteList[position], position)
        }
    }
}