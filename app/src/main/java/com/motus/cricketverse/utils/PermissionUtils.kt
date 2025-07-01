package com.motus.cricketverse.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    fun requestPermission(activity: Activity, permission: String, code: Int, onGranted: () -> Unit = {}) {
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            onGranted()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                AlertDialog.Builder(activity)
                    .setMessage("Permission required for full functionality")
                    .setPositiveButton("OK") { _, _ ->
                        ActivityCompat.requestPermissions(activity, arrayOf(permission), code)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), code)
            }
        }
    }

    fun handleResult(activity: Activity, requestCode: Int, grantResults: IntArray, expectedCode: Int, onGranted: () -> Unit = {}) {
        if (requestCode == expectedCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onGranted()
        } else if (requestCode == expectedCode) {
            AlertDialog.Builder(activity)
                .setMessage("Permission denied")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
