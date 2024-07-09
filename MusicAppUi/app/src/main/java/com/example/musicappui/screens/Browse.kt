package com.example.musicappui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappui.R

@Composable
fun BrowseScreen() {
    val category = listOf("Hilts", "Happy", "Yoga", "Workout", "Running", "TGIF")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(category) {
            LazyColumnItem(it, R.drawable.ic_browse)
        }
    }
}

@Composable
fun LazyColumnItem(cat: String, drawable: Int) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(180.dp)
            .border(3.dp, Color.DarkGray, shape = RoundedCornerShape(5.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(cat)
        Image(painter = painterResource(drawable), null)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBrowse() {
    BrowseScreen()
}