package com.romariomkk.nanitbirth.config

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

@com.bumptech.glide.annotation.GlideModule
class GlideAppConfigModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val exceptionHandlingStrategy = GlideExecutor.UncaughtThrowableStrategy { t ->
            Timber.e(GlideAppConfigModule::class.java.simpleName, t.message)
        }
        builder
            .setLogLevel(Log.VERBOSE)
            .setDiskCacheExecutor(
                GlideExecutor
                    .newDiskCacheBuilder()
                    .setUncaughtThrowableStrategy(exceptionHandlingStrategy)
                    .build()
            )
            .setDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.DEFAULT)
                    .disallowHardwareConfig()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
            )
    }

}