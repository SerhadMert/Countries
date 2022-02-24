package com.example.countries.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.navigation.fragment.navArgs
import com.example.countries.base.BaseFragment
import com.example.countries.databinding.FragmentDetailWebViewBinding

class DetailWebViewFragment : BaseFragment<FragmentDetailWebViewBinding>(FragmentDetailWebViewBinding::inflate) {

    private val args by navArgs<DetailWebViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews(){
        binding.webViewDetail.webViewClient = WebViewClient()
        binding.webViewDetail.settings.javaScriptEnabled = true
        args.url.let {
            binding.webViewDetail.loadUrl(it)
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            showLoading()
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            hideLoading()
        }
    }
}