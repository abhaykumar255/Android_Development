package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondScreen(collage : MyCollage,navigateToFirstScreen:() -> Unit,navigateToThirdScreen:()->Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Second screen ", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${collage.name}", fontSize = 20.sp)
        Text(text = "Roll No: ${collage.rollNo}", fontSize = 20.sp)
        Text(text = "Location: ${collage.location}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navigateToFirstScreen() }) {
            Text("Go to First Screen")
        }
        Button(onClick = { navigateToThirdScreen()}) {
            Text("Go to Third Screen")
        }
    }
}

