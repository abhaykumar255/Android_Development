package com.thread_jetpack.navigation

sealed class Routes(val routes : String) {
    object Home : Routes("home")
    object AddThreads : Routes("add_threads")
    object Notification : Routes("notification")
    object Profile : Routes("profile")
    object Search : Routes("search")
    object Splash : Routes("splash")
    object BottomNav : Routes("bottom_nav")
}