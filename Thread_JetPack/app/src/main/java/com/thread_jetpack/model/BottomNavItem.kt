package com.thread_jetpack.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.thread_jetpack.navigation.Routes

data class BottomNavItem(
    val title : String,
    val route : String,
    val icon : ImageVector
)
