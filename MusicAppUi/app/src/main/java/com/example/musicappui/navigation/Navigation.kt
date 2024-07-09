package com.example.musicappui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicappui.route.Screen
import com.example.musicappui.screens.AccountView
import com.example.musicappui.screens.BrowseScreen
import com.example.musicappui.screens.HomeView
import com.example.musicappui.screens.LibraryScreen
import com.example.musicappui.screens.SubscriptionScreen
import com.example.musicappui.viewModel.MainViewModel

@Composable
fun Navigation(
    navController: NavController,
    viewModel: MainViewModel,
    pd: PaddingValues
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Home.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.BottomScreen.Home.route) {
            HomeView()
        }
        composable(Screen.BottomScreen.Browse.route) {
            BrowseScreen()
        }
        composable(Screen.BottomScreen.Library.route) {
            LibraryScreen()
        }
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            SubscriptionScreen()
        }
    }
}