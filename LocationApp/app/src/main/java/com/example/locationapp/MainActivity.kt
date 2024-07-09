package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme
import com.example.locationapp.util.LocationUtil
import com.example.locationapp.viewModel.LocationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel = viewModel()
            LocationAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: LocationViewModel) {
    val context = LocalContext.current
    val locationUtil = LocationUtil(context)
    LocationDisplay(locationUtil = locationUtil, viewModel, context = context)
}

@Composable
fun LocationDisplay(
    locationUtil: LocationUtil,
    viewModel: LocationViewModel,
    context: Context
) {
    val locationData = viewModel.location.value

    val address = locationData?.let { locationUtil.reverseGeocodeLocation(it) }

    // it is registers to request to start an activity for result
    // this will open the pop and return the result
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        // this contract will starting an activity for intent for results
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        // this contract returns the permission
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                // have access for Location
                locationUtil.requestLocationUpdates(viewModel)
            } else {
                // ask for permission

                // rational -> Design a beautiful user interface using Android best practices

                val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    // so basically, don't open the permission rationale inside of another screen, do it in the main activity
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if (rationaleRequired) {
                    Toast.makeText(
                        context,
                        "Location permission is requires for this feature",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // now we need to go to the user setting on phone
                    Toast.makeText(
                        context,
                        "Location permission is requires, Please enable it in Android settings",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (locationData != null) {
            Text("Address is ${locationData.latitude} ${locationData.longitude}")
            Text("$address")
        } else {
            Text(text = "Location not available")
        }

        Button(onClick = {
            if (locationUtil.hasLocationAccess(context)) {
                // permission already granted
                locationUtil.requestLocationUpdates(viewModel)
            } else {
                // request location permission
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) {
            Text("Get Location")
        }
    }
}