package com.example.countries.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.countries.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Fragment.setActionBarTitle(string: String) {
    (requireActivity() as AppCompatActivity).supportActionBar?.title = string
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
    val dialogHeight = (height * 0.4f).toInt()
    layoutParams.width = dialogWidth
    layoutParams.height = dialogHeight
    this.window?.attributes = layoutParams
}

fun showDialog(
    context: Context,
    title: String? = "Uyarı",
    message: String?,
    cancellable: Boolean = true,
    action: () -> Unit = {}
) {
    AlertDialog.Builder(context).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancellable)
        setPositiveButton("Tamam") { _, _ -> action.invoke() }
    }.show()
}

fun ImageView.loadSvg(url: String?) {
    GlideToVectorYou
        .init()
        .with(this.context)
        .setPlaceHolder(R.drawable.ic_baseline_image_24, R.drawable.ic_baseline_image_24)
        .load(Uri.parse(url), this)
}

/**
 * flagImageUri returns http request, but when you click the url it opens with https
 * I can't check all the urls so i'm using a safeCall
 */
fun convert(string: String): String {
    var newString = string
    if (string.contains("http:")) {
        newString = string.replace("http", "https")
    }
    return newString
}

