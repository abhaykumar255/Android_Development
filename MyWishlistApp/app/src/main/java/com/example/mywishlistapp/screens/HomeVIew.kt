@file:OptIn(ExperimentalMaterialApi::class)

package com.example.mywishlistapp.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mywishlistapp.MainActivity
import com.example.mywishlistapp.component.AppBarView
import com.example.mywishlistapp.data.DummyList
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.route.Screen
import com.example.mywishlistapp.viewModel.WishViewModel

@Composable
fun HomeView(
    navController: NavHostController, viewModel: WishViewModel
) {
    val context = LocalContext.current
    Scaffold(topBar = {
        AppBarView("WishList") {
            Toast.makeText(context, "Button Pressed", Toast.LENGTH_SHORT).show()
        }
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            //Toast.makeText(context, "Clicking plus Icon", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.AddScreen.route + "/0L")
        },
            modifier = Modifier.padding(20.dp),
            contentColor = Color.White,
            containerColor = Color.Black,
            content = { Icon(Icons.Default.Add, contentDescription = null) })
    }) { it ->
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { wish -> wish.id }) { wish ->

                val dismissState = rememberDismissState(confirmStateChange = { values ->
                    if (values == DismissValue.DismissedToEnd || values == DismissValue.DismissedToStart) {
                        viewModel.deleteWish(wish)
                    }
                    true
                })

                SwipeToDismiss(state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp), contentAlignment = alignment
                        ) {
                            Icon(
                                Icons.Default.Delete, contentDescription = null, tint = Color.White
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                    dismissThresholds = { FractionalThreshold(0.45f) },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WishItem(wish: Wish, oncClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable { oncClick() }, elevation = 10.dp, backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = wish.title,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = wish.description,
                maxLines = 2,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

