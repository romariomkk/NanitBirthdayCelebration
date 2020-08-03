package com.romariomkk.nanitbirth.util.ext

import android.app.Activity
import com.romariomkk.nanitbirth.util.Keys

fun Activity.setFullScreen() {
    window.decorView.systemUiVisibility = Keys.FULLSCREEN_FLAGS
}