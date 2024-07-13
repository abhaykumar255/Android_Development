package com.thread_jetpack.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thread_jetpack.component.bottomAppBarList
import com.thread_jetpack.navigation.Routes

@Composable
fun BottomNav(navController: NavHostController) {

    val navController1 = rememberNavController()

    Scaffold(bottomBar = { MyBottomBar(navController1) }) { innerPaddings ->
        NavHost(
            navController = navController1,
            startDestination = Routes.Home.routes,
            modifier = Modifier.padding(innerPaddings)
        ) {
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
                 Profile(navController )
            }

            composable(Routes.Search.routes) {
                Search()
            }
        }
    }
}

@Composable
fun MyBottomBar(navController1: NavHostController) {

    // checking which item are in back trace entry currently
    val backStackEntry = navController1.currentBackStackEntryAsState()

    BottomAppBar {
        bottomAppBarList.forEach {
            // checking if it is selected or not
            val isSelected = it.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController1.navigate(it.route) {
                        // here we are handling the back navigation, when we are moving between the screen
                        popUpTo(navController1.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = it.icon, contentDescription = it.title) },
                label = { Text(it.title, maxLines = 1, overflow = TextOverflow.Ellipsis) })
        }
    }
}
