package com.thread_jetpack.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import com.thread_jetpack.model.BottomNavItem
import com.thread_jetpack.navigation.Routes

val bottomAppBarList = listOf(
    BottomNavItem(
        "Home",
        Routes.Home.routes,
        Icons.Rounded.Home
    ),
    BottomNavItem(
        "Search",
        Routes.Search.routes,
        Icons.Rounded.Search
    ),
    BottomNavItem(
        "Add Threads",
        Routes.AddThreads.routes,
        Icons.Rounded.Add
    ),
    BottomNavItem(
        "Notification",
        Routes.Notification.routes,
        Icons.Rounded.Notifications
    ),
    BottomNavItem(
        "Profile",
        Routes.Profile.routes,
        Icons.Rounded.Person
    ),
)