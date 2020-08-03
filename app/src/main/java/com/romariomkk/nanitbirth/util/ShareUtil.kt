package com.romariomkk.nanitbirth.util

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment


object ShareUtil {

    @JvmStatic
    fun Fragment.shareImage(imageUri: String) {
        val intent = Intent(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_STREAM, Uri.parse(imageUri))
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .setType("image/png")
        this.startActivity(Intent.createChooser(intent, "Share image"))
    }
}