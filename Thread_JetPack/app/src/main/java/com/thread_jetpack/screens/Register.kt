package com.thread_jetpack.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.thread_jetpack.R
import com.thread_jetpack.component.Constants.ALREADY_REGISTERED
import com.thread_jetpack.component.Constants.BIO_LABEL
import com.thread_jetpack.component.Constants.EMAIL_LABEL
import com.thread_jetpack.component.Constants.NAME_LABEL
import com.thread_jetpack.component.Constants.PASSWORD_LABEL
import com.thread_jetpack.component.Constants.REGISTER_HERE
import com.thread_jetpack.component.Constants.REGISTER_NOW
import com.thread_jetpack.component.Constants.USER_NAME_LABEL
import com.thread_jetpack.component.UiComponent.ElevatedButtonUi
import com.thread_jetpack.component.UiComponent.OutLineTextFieldBox
import com.thread_jetpack.component.UiComponent.TextButtonLabel
import com.thread_jetpack.navigation.Routes


@Composable
fun Register(navController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(REGISTER_HERE, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(12.dp))

        Image(painter = if (imageUri == null) painterResource(R.drawable.person)
        else rememberAsyncImagePainter(imageUri), contentDescription = "Person",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {
                    // here we will implement the logic for image selection, will be getting the image from gallery
                    val isGranted = ContextCompat.checkSelfPermission(
                        context,
                        permissionToResult
                    ) == PackageManager.PERMISSION_GRANTED

                    if (isGranted) {
                        launcher.launch("image/*")
                    } else {
                        permissionLauncher.launch(permissionToResult)
                    }
                }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // here we will be checking weather email id is unique or not
        OutLineTextFieldBox(email, { email = it }, EMAIL_LABEL, KeyboardType.Email)
        OutLineTextFieldBox(
            password,
            { password = it },
            PASSWORD_LABEL,
            KeyboardType.Password,
            true
        )
        OutLineTextFieldBox(userName, { userName = it }, USER_NAME_LABEL)
        OutLineTextFieldBox(name, { name = it }, NAME_LABEL)
        OutLineTextFieldBox(bio, { bio = it }, BIO_LABEL)

        Spacer(Modifier.height(10.dp))
        ElevatedButtonUi(REGISTER_NOW, onclick = {

        })

        Spacer(Modifier.height(10.dp))
        TextButtonLabel(ALREADY_REGISTERED, {
            // removing the register page on pressing again again on login and register
            navController.navigate(Routes.Login.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        })
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
