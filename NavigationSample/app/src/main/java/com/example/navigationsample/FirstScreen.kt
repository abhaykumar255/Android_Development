package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun FirstScreen(navigationToSecondScreen:(MyCollage)-> Unit,navigateToThirdScreen:()->Unit){
    var name by rememberSaveable { mutableStateOf("") }
    var rollNo by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .navigationBarsWithImePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the first screen ", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = rollNo, onValueChange = { rollNo = it }, label = { Text("Roll No") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val collage = MyCollage(name, rollNo.toIntOrNull() ?: 0, location)
            navigationToSecondScreen(collage)
        }) {
            Text("Go to Second Screen")
        }
        Button(onClick = { navigateToThirdScreen()}) {
            Text("Go to Third Screen")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFirst(){
    FirstScreen({},{})
}