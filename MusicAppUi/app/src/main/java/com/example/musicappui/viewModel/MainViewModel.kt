package com.example.musicappui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.musicappui.route.Screen

class MainViewModel : ViewModel() {
    private val _currentScreen: MutableState<Screen> =
        mutableStateOf(Screen.BottomScreen.Home)

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}