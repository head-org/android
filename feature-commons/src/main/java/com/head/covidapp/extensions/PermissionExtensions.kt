package com.head.covidapp.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Context.checkPermissions(permission: String): Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermission(permission: String, requestCode: Int = 1) {
    ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
}
