package com.retail.dolphinpos.presentation.util

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.retail.dolphinpos.presentation.R

object Utils {

    private var progressDialog: Dialog? = null

    // ✅ Show Loader
    fun showLoader(context: Context, message: String = "Loading...") {
        if (progressDialog?.isShowing == true) return

        progressDialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_progress)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            // Start animation on ImageView
            val imageView = findViewById<ImageView>(R.id.ivLoader)
            val anim = AnimationUtils.loadAnimation(context, R.anim.rotate)
            imageView.startAnimation(anim)

            // Set message text
            findViewById<TextView>(R.id.tvMessage)?.text = message
            show()
        }
    }

    // ✅ Hide Loader
    fun hideLoader() {
        progressDialog?.dismiss()
        progressDialog = null
    }


    // ✅ Error Dialog
    fun showErrorDialog(
        context: Context,
        title: String = "Alert",
        message: String,
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    // ✅ Handle Button Disability
    fun Button.setEnabledWithAlpha(isEnabled: Boolean, alphaWhenDisabled: Float = 0.4f) {
        this.isEnabled = isEnabled
        this.alpha = if (isEnabled) 1f else alphaWhenDisabled
    }

}