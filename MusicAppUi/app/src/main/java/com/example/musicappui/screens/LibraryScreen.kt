package com.example.musicappui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappui.component.DummyLibrary
import com.example.musicappui.component.dummyList

@Composable
fun LibraryScreen() {
    LazyColumn {
        items(dummyList) {
            LazyItemsColumn(it)
        }
    }
}

@Composable
fun LazyItemsColumn(item: DummyLibrary) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(item.icon), null)
                Text(item.name)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, null)
        }
        Divider(thickness = 1.dp, color = Color.DarkGray)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLibrary() {
    LibraryScreen()
}