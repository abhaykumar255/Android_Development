package com.thread_jetpack.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.thread_jetpack.R
import com.thread_jetpack.component.ADD_THREADS
import com.thread_jetpack.component.Constants.LOGIN
import com.thread_jetpack.component.SharedPrefConstants
import com.thread_jetpack.navigation.Routes
import com.thread_jetpack.utils.SharedfPref
import com.thread_jetpack.viewModel.AddThreadViewModel

@Composable
fun AddThreads(navController: NavHostController) {
    val addThreadViewModel : AddThreadViewModel = viewModel()
    val isPosted by addThreadViewModel.isPosted.observeAsState(false)
    val context = LocalContext.current
    var threads by remember { mutableStateOf("") }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    val permissionToResult =
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES
        else Manifest.permission.READ_EXTERNAL_STORAGE

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                launcher.launch("image/*")
            } else {
                // or we will open the dialog box to go to the setting if not granted
                Toast.makeText(context, "Required permission", Toast.LENGTH_SHORT).show()
            }
        }


    LaunchedEffect(isPosted) {
        if (isPosted){
            threads = ""
            imageUri = null
            Toast.makeText(context,"Thread Added",Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.Home.routes){
                popUpTo(Routes.AddThreads.routes){
                    inclusive = true
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (crossPic, addThreadText, logo, userName, editText, attachMedia,
            replyText, button, imageBox) = createRefs()

        Image(
            painter = painterResource(R.drawable.close_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(crossPic) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable {
                    // closing the add threads
                    navController.navigate(Routes.Home.routes){
                        popUpTo(Routes.AddThreads.routes){
                            inclusive = true
                        }
                    }
                }
        )
        Text(
            ADD_THREADS,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.constrainAs(addThreadText) {
                start.linkTo(crossPic.end, margin = 10.dp)
                top.linkTo(crossPic.top)
                bottom.linkTo(crossPic.bottom)
            }
        )

        Image(
            painter = rememberAsyncImagePainter(
                SharedfPref.getPrefData(
                    SharedPrefConstants.IMAGE,
                    context
                )
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(addThreadText.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                }
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            SharedfPref.getPrefData(SharedPrefConstants.USER_NAME, context),
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.constrainAs(userName) {
                start.linkTo(logo.end, margin = 10.dp)
                top.linkTo(logo.top)
                bottom.linkTo(logo.bottom)
            }
        )

        BasicTextFieldWithHint(
            hint = "Start a thread ...",
            value = threads,
            onValueChange = { threads = it },
            modifier = Modifier
                .constrainAs(editText) {
                    top.linkTo(userName.bottom)
                    start.linkTo(userName.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )

        if (imageUri == null) {
            Image(
                painter = painterResource(R.drawable.attach_icon),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(attachMedia) {
                        top.linkTo(editText.bottom)
                        start.linkTo(editText.start)
                    }
                    .clickable {
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
        } else {
            Box(modifier = Modifier
                .background(Color.Gray)
                .padding(1.dp)
                .constrainAs(imageBox) {
                    top.linkTo(editText.bottom)
                    start.linkTo(editText.start)
                    end.linkTo(parent.end)
                }
                .height(250.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    Icons.Default.Close,
                    null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            imageUri = null
                        })
            }
        }

        Text(
            "Any one can reply ?",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.constrainAs(replyText) {
                start.linkTo(parent.start, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            }
        )

        TextButton(onClick = {
            if (imageUri == null){
                addThreadViewModel.saveData(threads,FirebaseAuth.getInstance().currentUser!!.uid,"")
            }else{
                addThreadViewModel.saveImage(threads,FirebaseAuth.getInstance().currentUser!!.uid,imageUri)
            }
        }, modifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            Text(
                "Post",
                style = TextStyle(fontSize = 20.sp),
            )
        }

    }
}

@Composable
fun BasicTextFieldWithHint(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (value.isEmpty()) {
            Text(hint, color = Color.Gray)
        }
        BasicTextField(
            value = value, onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
