package com.romariomkk.nanitbirth.util.imgpick

import android.Manifest
import android.content.Intent
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.TedPermission
import com.romariomkk.nanitbirth.util.permission.PermissionUtils
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import java.io.File
import java.lang.ref.WeakReference

class ImagePickingDelegate<T>(fragment: T)
        where T : Fragment, T : ImagePickingDelegate.Requester {

    private val frag = WeakReference(fragment)

    private val easyImage by lazy {
        EasyImage.Builder(fragment.requireContext())
            .allowMultiple(false)
            .build()
    }

    fun pickImageFromCamera() {
        frag.get()?.let {

            if (TedPermission.isGranted(it.requireContext(), *CAMERA_PERMISSIONS)) {
                easyImage.openCameraForImage(it)
                return
            }

            PermissionUtils.checkPermissionsAndExecute(
                it.requireContext(),
                *CAMERA_PERMISSIONS,
                onGranted = {
                    easyImage.openCameraForImage(it)
                })
        }
    }

    fun pickImageFromGallery() {
        frag.get()?.let {

            if (TedPermission.isGranted(it.requireContext(), *GALLERY_PERMISSIONS)) {
                easyImage.openGallery(it)
                return
            }

            PermissionUtils.checkPermissionsAndExecute(it.requireContext(),
                *GALLERY_PERMISSIONS,
                onGranted = {
                    easyImage.openGallery(it)
                })
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        frag.get()?.let { fragment ->
            easyImage.handleActivityResult(
                requestCode, resultCode, data, fragment.requireActivity(),
                object : DefaultCallback() {
                    override fun onMediaFilesPicked(
                        imageFiles: Array<MediaFile>,
                        source: MediaSource
                    ) {
                        ImageRotationUtil.applyRotationIfNeeded(imageFiles[0].file)
                        frag.get()?.onImagePicked(imageFiles[0].file)
                    }

                    override fun onImagePickerError(error: Throwable, source: MediaSource) {
                        error.printStackTrace()
                        frag.get()?.onImageRequestCancel()
                    }

                    override fun onCanceled(source: MediaSource) {
                        frag.get()?.onImageRequestCancel()
                    }
                })
        }
    }

    interface Requester {
        fun onImagePicked(file: File)
        fun onImageRequestCancel()
    }

    companion object {
        val CAMERA_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val GALLERY_PERMISSIONS = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}