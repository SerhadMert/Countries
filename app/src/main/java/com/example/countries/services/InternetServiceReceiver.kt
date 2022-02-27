package com.example.countries.services

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.countries.ui.dialog.NoConnectionDialog
import com.example.countries.utils.showWithAttrs

class InternetServiceReceiver : BroadcastReceiver() {

    lateinit var dialog: NoConnectionDialog

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!InternetServiceManager.isOnline(context)) {
            showDialog(context)
        } else {
            hideDialog()
        }
    }

    private fun hideDialog() {
        if (::dialog.isInitialized) {
            dialog.dismiss()
        }
    }

    private fun showDialog(context: Context?) {
        context?.let {
            if (!::dialog.isInitialized) {
                dialog = NoConnectionDialog(context)
                dialog.setCancelable(false)
            }
            if (!dialog.isShowing) {
                dialog.showWithAttrs(context as Activity)
            }
        }
    }
}