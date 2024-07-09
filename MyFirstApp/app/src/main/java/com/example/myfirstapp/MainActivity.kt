package com.example.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
        enableEdgeToEdge()
        setContent {
            MyFirstApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFirstApp(){
    val context = LocalContext.current
    MyFirstAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Hello First App") }, colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(
                        context,"Pressed +",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            }) { innerPadding ->
            Greeting(
                name = "Abhay k Rajput",
                name2 = "Abhay Singh Rajput",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun Greeting(name: String, name2: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.border(2.dp, Color.Black)
        ) {
            Text(
                text = "Welcome $name!", modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "This is $name2.", modifier = Modifier.padding(bottom = 15.dp)
            )
        }
    }

}

@Preview(showSystemUi = true, name = "Sample1", showBackground = true)
@Composable
fun PreviewSample() {
    MyFirstApp()
}