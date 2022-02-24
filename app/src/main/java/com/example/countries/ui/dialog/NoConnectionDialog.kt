package com.example.countries.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.countries.R
import com.example.countries.databinding.DialogNoConnectionBinding

@SuppressLint("ResourceType")
class NoConnectionDialog(context: Context) :
    Dialog(context, R.layout.dialog_no_connection) {

    private lateinit var binding: DialogNoConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogNoConnectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnGoOn.setOnClickListener {
            if (this.isShowing) this.dismiss()
        }
    }
}