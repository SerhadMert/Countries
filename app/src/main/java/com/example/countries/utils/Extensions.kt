package com.example.countries.utils

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

fun View.show() {
    visibility = View.VISIBLE
}
fun View.gone() {
    visibility = View.GONE
}
fun Dialog.showWithAttrs(activity: Activity) {
    this.show()
    val displayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val display = activity.display
        display?.getRealMetrics(displayMetrics)
    } else {
        @Suppress("DEPRECATION")
        val display = activity.windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(displayMetrics)
    }
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    val layoutParams = WindowManager.LayoutParams()
    layoutParams.copyFrom(this.window?.attributes)
    val dialogWidth = (width * 0.9f).toInt()
    val dialogHeight = (height *0.4f).toInt()
    layoutParams.width = dialogWidth
    layoutParams.height = dialogHeight
    this.window?.attributes = layoutParams
}