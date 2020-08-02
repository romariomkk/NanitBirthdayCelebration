package com.romariomkk.nanitbirth.util.imgpick

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream


object ImageRotationUtil {

    fun applyRotationIfNeeded(imageFile: File) {
        val exif = ExifInterface(imageFile.absolutePath)
        val rotation = when (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
        if (rotation == 0)
            return

        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
        val matrix = Matrix().apply { postRotate(rotation.toFloat()) }
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()

        lateinit var out: FileOutputStream
        try {
            out = FileOutputStream(imageFile)
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        } catch (e: Exception) {
            Timber.e(e)
        } finally {
            rotatedBitmap.recycle()
            out.close()
        }
    }

}