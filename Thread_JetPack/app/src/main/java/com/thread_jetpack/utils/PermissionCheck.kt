package com.thread_jetpack.utils

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.thread_jetpack.screens.PermissionDialog

@Composable
fun PermissionHandle(){
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    // this is for getting the image
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    // checking the requested permission
    val permissionToResult =
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES
        else Manifest.permission.READ_EXTERNAL_STORAGE

    // checking the requested permission
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                launcher.launch("image/*")
            } else {
                // or we will open the dialog box to go to the setting if not granted
                showDialog.value = true
            }
        }
    if (showDialog.value) {
        PermissionDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun PermissionDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Permission Required") },
        text = { Text("This permission is required to select an image from your gallery. Please grant the permission in the app settings.") },
        confirmButton = {
            Button(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text("Open Settings")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}