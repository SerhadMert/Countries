package com.example.countries.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.countries.base.BaseFragment
import com.example.countries.data.entity.countrydetail.Data
import com.example.countries.databinding.FragmentDetailBinding
import com.example.countries.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModels()
    private val safeArgs by navArgs<DetailFragmentArgs>()
    private var url = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(safeArgs.name)
        getCountryDetailByCode()
        initClickListener()
    }

    private fun getCountryDetailByCode() {
        viewModel.getCountryDetailByCode(safeArgs.code)
            .observe(viewLifecycleOwner) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        hideLoading()
                        setData(response.data?.data)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        showDialog(requireContext(), message = "${response.message}")
                    }
                }
            }
    }

    private fun setData(data: Data?) = with(binding) {
        tvCountryCode.text = data?.code
        imgCountry.loadSvg(convert(data?.flagImageUri ?: ""))
        url = "${Constants.WIKIDATA_URL}${data?.wikiDataId}"
    }

    private fun initClickListener() {
        binding.btnMoreInfo.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToDetailWebViewFragment(url)
            findNavController().navigate(action)
        }
    }
}
