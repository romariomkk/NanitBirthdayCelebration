package com.romariomkk.nanitbirth

import android.app.Application
import com.romariomkk.nanitbirth.config.initializer.Initializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class NanitBirthApp: Application() {

    @Inject
    lateinit var initializerSet: Set<@JvmSuppressWildcards Initializer>

    override fun onCreate() {
        super.onCreate()
        initializerSet.forEach {
            it.init(this)
        }
    }
}