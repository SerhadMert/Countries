package com.example.countries.base

import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.countries.MainActivity
import com.example.countries.R
import com.example.countries.services.InternetServiceReceiver
import com.example.countries.ui.dialog.LoadingProgress

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(),IBottomBarInterface {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private val receiver = InternetServiceReceiver()
    private var loadingProgressBar: LoadingProgress? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        requireActivity().registerReceiver(receiver, filter)
        initProgressBar()
        return binding.root
    }

    private fun initProgressBar() {
        loadingProgressBar = LoadingProgress(requireContext())
        loadingProgressBar?.setCancelable(false)
        loadingProgressBar?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showLoading() {
        if (loadingProgressBar?.isShowing == false) {
            loadingProgressBar?.show()
        }
    }

    fun hideLoading() {
        if (loadingProgressBar?.isShowing == true) {
            loadingProgressBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        if (isNavigationBarVisible())
            (activity as MainActivity).showNavigationBar()
        else
            (activity as MainActivity).hideNavigationBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().unregisterReceiver(receiver)
    }
}