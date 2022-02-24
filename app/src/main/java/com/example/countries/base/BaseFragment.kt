package com.example.countries.base

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.countries.MainActivity
import com.example.countries.services.InternetServiceReceiver

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(),IBottomBarInterface {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private val receiver = InternetServiceReceiver()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        requireActivity().registerReceiver(receiver, filter)
        return binding.root
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