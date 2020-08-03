package com.romariomkk.nanitbirth.util.ext

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.provider.MediaStore
import android.view.View
import timber.log.Timber
import java.io.OutputStream
import java.util.*


fun View.takeScreenShot(): String? {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap.save(context, Date().time.toString())
}

fun Bitmap.save(context: Context, filename: String): String? {
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.TITLE, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

    val outStream: OutputStream?
    uri?.let {
        try {
            outStream = context.contentResolver.openOutputStream(uri)
            this.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
            outStream?.close()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    return uri.toString()
}