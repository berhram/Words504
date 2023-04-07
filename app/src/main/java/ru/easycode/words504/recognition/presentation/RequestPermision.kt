package ru.easycode.words504.recognition.presentation

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


interface RequestPermission {
    fun handle(activity: AppCompatActivity, launcher: ActivityResultLauncher<String>)

    class UnderM(private val handlePermissionGranted: HandlePermissionGranted) : RequestPermission {
        override fun handle(activity: AppCompatActivity, launcher: ActivityResultLauncher<String>) {
            handlePermissionGranted.permissionCallback(true)
        }
    }

    class AboveM(
        private val permission: String,
        private val handlePermissionGranted: HandlePermissionGranted
    ) : RequestPermission {
        override fun handle(activity: AppCompatActivity, launcher: ActivityResultLauncher<String>) =
            with(activity) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                )
                    handlePermissionGranted.permissionCallback(true)
                else {
                    launcher.launch(permission)
                }
            }
    }

}

interface HandlePermissionGranted {
    fun permissionCallback(granted: Boolean)
}