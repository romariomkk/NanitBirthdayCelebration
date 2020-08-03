package com.romariomkk.nanitbirth.config.initializer

import android.content.Context
import com.romariomkk.nanitbirth.BuildConfig
import timber.log.Timber

class TimberInitializer: Initializer {
    override fun init(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}