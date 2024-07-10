package com.thread_jetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.thread_jetpack.screens.AddThreads
import com.thread_jetpack.screens.BottomNav
import com.thread_jetpack.screens.Home
import com.thread_jetpack.screens.Notification
import com.thread_jetpack.screens.Profile
import com.thread_jetpack.screens.Search
import com.thread_jetpack.screens.Splash

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Splash.routes) {

        composable(Routes.Splash.routes) {
            Splash(navController)
        }

        composable(Routes.AddThreads.routes) {
            AddThreads()
        }

        composable(Routes.Home.routes) {
            Home()
        }

        composable(Routes.Notification.routes) {
            Notification()
        }

        composable(Routes.Profile.routes) {
            Profile()
        }

        composable(Routes.Search.routes) {
            Search()
        }

        composable(Routes.BottomNav.routes){
            BottomNav(navController)
        }
    }
}