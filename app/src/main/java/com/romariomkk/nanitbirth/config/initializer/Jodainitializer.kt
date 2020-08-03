package com.romariomkk.nanitbirth.config.initializer

import android.content.Context
import net.danlew.android.joda.JodaTimeAndroid

class Jodainitializer: Initializer {
    override fun init(context: Context) {
        JodaTimeAndroid.init(context)
    }
}