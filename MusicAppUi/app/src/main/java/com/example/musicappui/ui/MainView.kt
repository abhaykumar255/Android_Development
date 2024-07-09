package com.example.musicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.navigation.Navigation
import com.example.musicappui.route.Screen
import com.example.musicappui.route.screensInBottom
import com.example.musicappui.route.screensInDrawer
import com.example.musicappui.screens.AccountDialog
import com.example.musicappui.viewModel.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    // These three lines, finds out which view we currently are.
    val navController: NavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val dialogOpen = remember {
        mutableStateOf(false)
    }

    // changing the current Screen title
    val title = remember { mutableStateOf(currentScreen.title) }


    // for bottomsheet modal
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    // bottom sheet modal state
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded })
    val roundedCorner = if (isSheetFullScreen) 0.dp else 12.dp

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            // this will take care of screens that you have in bottom
            BottomNavigation(Modifier.wrapContentSize()) {
                screensInBottom.forEach { item ->
                    val isSelected = currentRoute == item.bRoute
                    val tint = if (isSelected) Color.White else Color.Black
                    BottomNavigationItem(selected = isSelected, onClick = {
                        title.value = item.bTitle
                        navController.navigate(item.bRoute)
                    }, icon = {
                        Icon(
                            contentDescription = item.bTitle,
                            painter = painterResource(id = item.icon),
                            tint = tint
                        )
                    }, label = {
                        Text(item.bTitle, color = tint)
                    }, selectedContentColor = Color.White, unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            MoreBottomSheet(modifier = modifier)
        }, sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCorner, topEnd = roundedCorner)
    ) {
        Scaffold(
            bottomBar = bottomBar, scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(title = { Text(title.value) }, navigationIcon = {
                    IconButton(onClick = {
                        /* Open the drawer */
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Menu Button")
                    }
                },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if (modalSheetState.isVisible)
                                    modalSheetState.hide()
                                else
                                    modalSheetState.show()
                            }
                        }) {
                            Icon(Icons.Default.MoreVert, null)
                        }
                    }
                )
            },
            drawerContent = {
                LazyColumn(Modifier.padding(10.dp)) {
                    items(screensInDrawer) { itemScreen ->
                        DrawerItem(
                            selected = currentRoute == itemScreen.dRoute, item = itemScreen
                        ) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (itemScreen.dRoute == Screen.DrawerScreen.AddAccount.route) {
                                // open dialog
                                dialogOpen.value = true
                            } else {
                                navController.navigate(itemScreen.dRoute)
                                title.value = itemScreen.dTitle
                            }
                        }
                    }
                }
            },
        ) {
            Navigation(navController = navController, viewModel = viewModel, pd = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }
}

@Composable
fun MoreBottomSheet(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                MaterialTheme.colors.primarySurface
            )
    ) {
        Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = modifier.padding(16.dp)) {
                Icon(Icons.Default.Settings, null)
                Text("Settings", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = modifier.padding(16.dp)) {
                Icon(Icons.Default.Share, null)
                Text("Shares", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = modifier.padding(16.dp)) {
                Icon(Icons.Default.Phone, null)
                Text("Help", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean, item: Screen.DrawerScreen, onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.Gray else Color.White
    Row(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable { onDrawerItemClicked() }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(text = item.title, style = MaterialTheme.typography.h5)
    }
}

//        Scaffold(
//        bottomBar = bottomBar,
//        scaffoldState = scaffoldState,
//        topBar = {
//            TopAppBar(
//                title = { Text(title.value) },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        /* Open the drawer */
//                        scope.launch {
//                            scaffoldState.drawerState.open()
//                        }
//                    }) {
//                        Icon(Icons.Default.AccountCircle, contentDescription = "Menu Button")
//                    }
//                }
//            )
//        },
//        drawerContent = {
//            LazyColumn(Modifier.padding(10.dp)) {
//                items(screensInDrawer) { itemScreen ->
//                    DrawerItem(selected = currentRoute == itemScreen.dRoute, item = itemScreen) {
//                        scope.launch {
//                            scaffoldState.drawerState.close()
//                        }
//                        if (itemScreen.dRoute == Screen.DrawerScreen.AddAccount.route) {
//                            // open dialog
//                            dialogOpen.value = true
//                        } else {
//                            navController.navigate(itemScreen.dRoute)
//                            title.value = itemScreen.dTitle
//                        }
//                    }
//                }
//            }
//        }
//    ) {
//        Navigation(navController = navController, viewModel = viewModel, pd = it)
//        AccountDialog(dialogOpen = dialogOpen)
//    }
