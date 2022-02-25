package com.example.countries.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.countries.base.BaseFragment
import com.example.countries.data.entity.countries.CountriesData
import com.example.countries.databinding.FragmentFavoritesBinding
import com.example.countries.utils.SwipeToCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate),IFavoriteItem {

    private val viewModel: FavoritesViewModel by viewModels()
    private val favoritesAdapter by lazy { FavoritesListAdapter(this) }
    private lateinit var favoriteList: ArrayList<CountriesData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavorites()
        initRV()
        initSwipe()
    }

    private fun getFavorites(){
        favoriteList = viewModel.getFavorites() as ArrayList<CountriesData>
        favoritesAdapter.setData(favoriteList)
    }

    private fun initRV(){
        binding.rvFavorites.adapter = favoritesAdapter
    }

    private fun initSwipe(){
        val itemTouchHelper = ItemTouchHelper(SwipeToCard(favoritesAdapter,requireContext()))
        itemTouchHelper.attachToRecyclerView(binding.rvFavorites)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun favoriteItem(item: CountriesData, position: Int) {
        viewModel.deleteFromFavorites(item)
        favoriteList.removeAt(position)
        favoritesAdapter.setData(favoriteList)
        favoritesAdapter.notifyItemRemoved(position)
        favoritesAdapter.notifyDataSetChanged()
    }
}