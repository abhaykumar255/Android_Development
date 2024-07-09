package com.example.musicappui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CalendarLocale
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.R
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView() {
    val category = listOf("Hilts", "Happy", "Yoga", "Workout", "Running", "TGIF")
    val grouped = listOf("New Release", "Favourite", "Top Rated").groupBy {
        it[0]
    }

    LazyColumn {
        grouped.forEach {
            // stickHeader -> sticks the header until the next header came
            stickyHeader {
                Text(it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(category) { cat ->
                        BrowseItem(cat, drawable = R.drawable.ic_browse)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowseItem(cat: String, drawable: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp), border = BorderStroke(3.dp, Color.DarkGray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(cat)
            Image(painter = painterResource(id = drawable), null)
        }
    }
}