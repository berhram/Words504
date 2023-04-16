package ru.easycode.words504.recognition.presentation

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.recognition.STTState


interface RequestPermission {
    fun handle(fragment: Fragment, launcher: ActivityResultLauncher<String>)

    class UnderM(private val handlePermissionGranted: HandlePermissionGranted) : RequestPermission {
        override fun handle(fragment: Fragment, launcher: ActivityResultLauncher<String>) {
            handlePermissionGranted.permissionCallback(true)
        }
    }

    class AboveM(
        private val permission: String,
        private val handlePermissionGranted: HandlePermissionGranted
    ) : RequestPermission {
        override fun handle(fragment: Fragment, launcher: ActivityResultLauncher<String>) =
            with(fragment) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
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

    class Empty : HandlePermissionGranted {
        override fun permissionCallback(granted: Boolean) = Unit
    }
}

interface ObserveRequestPermission {

    fun observeRequestPermission(owner: LifecycleOwner, observer: Observer<RequestPermission>)

}