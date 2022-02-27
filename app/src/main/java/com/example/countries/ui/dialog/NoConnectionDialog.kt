package com.example.countries.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.countries.databinding.DialogNoConnectionBinding

@SuppressLint("ResourceType")
class NoConnectionDialog(context: Context) :
    Dialog(context) {

    private lateinit var binding: DialogNoConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogNoConnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGoOn.setOnClickListener {
            if (this.isShowing) this.dismiss()
        }
    }
}