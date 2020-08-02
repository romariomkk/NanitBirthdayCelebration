package com.romariomkk.nanitbirth.util.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import timber.log.Timber
import java.io.*
import java.util.*


fun View.takeScreenShot(): String? {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap.save(context, Date().time.toString())
}

fun Bitmap.save(context: Context, filename: String): String? {
    val path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + "/" + filename
    val imageFile = File(path)
    var out: OutputStream? = null

    try {
        out = FileOutputStream(imageFile)
        this.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        return path
    } catch (e: FileNotFoundException) {
        Timber.e(e)
        return null
    } catch (e: IOException) {
        Timber.e(e)
        return null
    } finally {
        try {
            out?.close()
        } catch (exc: Exception) {
            return null
        }
    }
}