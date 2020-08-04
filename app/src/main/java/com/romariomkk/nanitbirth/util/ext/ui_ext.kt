package com.romariomkk.nanitbirth.util.ext

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.romariomkk.nanitbirth.R

fun Activity.setStatusBarColor(@ColorRes color: Int) {
    window.statusBarColor = ContextCompat.getColor(this, color)
}

fun Activity.clearStatusBarColor() {
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
}