package com.thread_jetpack.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.thread_jetpack.itemView.ThreadItem
import com.thread_jetpack.viewModel.HomeViewModel

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = viewModel()
    val threadAndUsers by homeViewModel.threadsAndUsers.observeAsState(emptyList())

    LazyColumn {
        items(threadAndUsers) { pairs ->
            ThreadItem(
                threadModel = pairs.first,
                userModel = pairs.second,
                FirebaseAuth.getInstance().currentUser!!.uid,
                navController
            )
        }
    }
}

