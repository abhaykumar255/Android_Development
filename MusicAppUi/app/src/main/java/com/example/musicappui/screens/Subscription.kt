package com.example.musicappui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionScreen() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Manage Subscription")
        Spacer(Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Musical ")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Free Tier")
                    TextButton(onClick = {}) {
                        Row() {
                            Text("See All Plans")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Icon(Icons.Default.AccountBox, contentDescription = null)
                    Text("Get A Plan")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSubscription() {
    SubscriptionScreen()
}

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 5.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column(Modifier.padding(bottom = 30.dp)) {
//                        Text("Musical ")
//                        Text("Free Tier")
//                    }
//                    TextButton(onClick = {}) {
//                        Row(
//                            modifier = Modifier.padding(),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text("See All Plans")
//                            Icon(
//                                imageVector = Icons.Default.KeyboardArrowRight,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//                Divider(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp))
//                Row(modifier = Modifier.padding(bottom = 16.dp, start = 5.dp)) {
//                    Icon(Icons.Default.AccountBox,contentDescription = null)
//                    Text("Get A Plan")
//                }