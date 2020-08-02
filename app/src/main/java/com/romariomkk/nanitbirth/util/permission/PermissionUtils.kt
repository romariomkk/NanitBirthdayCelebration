package com.romariomkk.nanitbirth.util.permission

import android.content.Context
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

object PermissionUtils {

    @JvmStatic
    fun checkPermissionsAndExecute(
        context: Context, vararg permissions: String,
        onGranted: () -> Unit, onDeclined: (() -> Unit)? = null
    ) {
        TedPermission.with(context)
            .setPermissions(*permissions)
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    onGranted.invoke()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    onDeclined?.invoke()
                }
            }).check()
    }
}